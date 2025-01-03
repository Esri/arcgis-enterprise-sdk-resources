using ESRI.ArcGIS.Geodatabase;
using ESRI.ArcGIS.Geometry;
using ESRI.Server.SOESupport;
using System;
using System.Collections.Generic;

namespace TopologyRESTSOE
{
  /// <summary>
  /// Class contains the topology API related methods
  /// </summary>
  public class TopologyOperation
  {
    /// <summary>
    /// Traverse the topology graph, <see cref="ITopologyGraph"/>, to visit topology elements <see cref="ITopologyElement"/>
    /// </summary>
    /// <param name="featureDataset">Feature dataset object</param>
    /// <param name="topologyName">The topology name</param>
    /// <param name="featureClassName">The feature class name</param>
    /// <param name="originFeatureObjectId">Feature ObjectId from the feature class, <paramref name="featureClassName"/></param>
    /// <returns>A JSON object <see cref="JsonObject"/> containing parcels' id and address</returns>
    /// <remarks> 
    /// This method builds the topology graph around origin parcel feature and traverse the topology graph to fetch its adjoining parcels and their addresses
    /// </remarks>
    public JsonObject TraverseTopologyGraph(IFeatureDataset featureDataset, string topologyName, string featureClassName, int originFeatureObjectId)
    {
      // Fetch feature class by name 
      IFeatureClass parcelFeatureClass = GetFeatureClass(featureDataset, featureClassName);

      // Fetch topology by name from feature dataset 
      ITopology topology = GetTopologyFromFeatureDataset(featureDataset, topologyName);

      // Get the origin parcel feature identified by ObjectID
      IFeature parcelFeature = GetFeature(parcelFeatureClass, originFeatureObjectId);

      ITopologyGraph topologyGraph = topology.Cache;

      // Build the topology graph around the origin parcel feature
      topologyGraph.Build(parcelFeature.Shape.Envelope, false);

      IEnumTopologyEdge enumTopologyEdge = topologyGraph.GetParentEdges(parcelFeatureClass, originFeatureObjectId);
      enumTopologyEdge.Reset();

      List<dynamic> taxParcelIds = new List<dynamic>();

      IEnvelope topologyParentsEnvelope = new EnvelopeClass();
      Dictionary<long, IPoint> parcelsToCentroidMap = new Dictionary<long, IPoint>();

      for (int topoEdgeCount = 0; topoEdgeCount < enumTopologyEdge.Count; topoEdgeCount++)
      {
        ITopologyEdge topologyEdge = enumTopologyEdge.Next();

        // Parents of the topology edge
        IEnumTopologyParent parents = topologyEdge.Parents;
        parents.Reset();

        for (int parentsCount = 0; parentsCount < parents.Count; parentsCount++)
        {
          esriTopologyParent parent = parents.Next();
          long parentFID = parent.m_FID;
          IFeatureClass parentFC = parent.m_pFC;
          IFeature parentParcelFeature = parentFC.GetFeature(parentFID);

          // Get the index of 'ParcelType' field from the parcel feature class
          int parcelTypeIndex = parentParcelFeature.Fields.FindField("PARCELTYPE");

          // Get parcel type value
          int parcelTypeValue = Convert.ToInt32(parentParcelFeature.Value[parcelTypeIndex]);

          // Avoid duplicates and skip parcels with 'RowOverlap' subtype
          if (parentFC == parcelFeatureClass && !taxParcelIds.Contains(parentFID) && parentFID != originFeatureObjectId && (parcelTypeValue < 8 && parcelTypeValue > 0))
          {
            taxParcelIds.Add(parentFID);

            // Envelope of a parcel 
            IEnvelope parcelEnvelope = parentParcelFeature.Extent.Envelope;

            // Centroid of a parcel
            IArea parcelArea = parcelEnvelope as IArea;
            IPoint parcelCentroid = parcelArea.Centroid;

            // Add parcel id and centroid to the mapping dictionary
            parcelsToCentroidMap.Add(parentFID, parcelCentroid);

            // Union of the adjoining parcels' envelope
            topologyParentsEnvelope.Union(parcelEnvelope);
          }
        }
      }

      // Update topology graph to include adjoining parcels 
      topologyGraph.Build(topologyParentsEnvelope, false);

      JsonObject jsonObject = new JsonObject();

      // Get adjoining parcels and their addresses
      List<string> parcelWithAddressList = GetParcelsWithAddress(topologyGraph, parcelsToCentroidMap);

      // Format JSON array
      jsonObject.AddArray($"Adjoining parcels of {originFeatureObjectId}", parcelWithAddressList?.ToArray());
      return jsonObject;
    }

    /// <summary>
    /// Fetch existing topology from a feature dataset
    /// </summary>
    /// <param name="featureDataset">Feature dataset</param>
    /// <param name="topologyName">Existing topology name</param>
    /// <returns>A topology <see cref="ITopology"/>if found otherwise null</returns>
    private ITopology GetTopologyFromFeatureDataset(IFeatureDataset featureDataset, string topologyName)
    {
      ITopology topology = null;

      ITopologyContainer topologyContainer = (ITopologyContainer)featureDataset;

      // Fetch existing topology by name 
      if (topologyContainer.TopologyCount > 0)
      {
        topology = topologyContainer.TopologyByName[topologyName];
      }      

      return topology;
    }


    /// <summary>
    /// Provides a list of parcel id with associated address in the formatted string
    /// </summary>
    /// <param name="topologyGraph">The topology graph of the area of interest. <see cref="ITopologyGraph"/></param>
    /// <param name="parcelsToCentroidMap">A dictionary object containing the parcel id and its centroid</param>
    /// <returns>A formatted list of parcel and its address information</returns>
    private List<string> GetParcelsWithAddress(ITopologyGraph topologyGraph, Dictionary<long, IPoint> parcelsToCentroidMap)
    {
      List<string> parcelWithAddressList = new List<string>();

      if (topologyGraph == null || parcelsToCentroidMap.Count < 1) return null;

      // Iterating parcels to fetch the address node from the topology graph
      foreach (KeyValuePair<long, IPoint> keyValuePair in parcelsToCentroidMap)
      {
        long parcelID = keyValuePair.Key;
        IPoint parcelCentroid = keyValuePair.Value;

        ITopologyElement addressToplogyElement;
        double hitRadius;

        // Find a topology element that is closest to the parcel centroid  and with in the search radius of 10.0. If success the method will return the address topology node
        bool isElementFound = topologyGraph.HitTest((int)esriTopologyElementType.esriTopologyNode, parcelCentroid, 10.0, null, out hitRadius, out addressToplogyElement);

        if (addressToplogyElement != null && isElementFound)
        {
          IEnumTopologyParent addressElementParents = addressToplogyElement.Parents;
          addressElementParents.Reset();
          
          //Iterate over topology element parents
          for (int addressElementCount = 0; addressElementCount < addressElementParents.Count; addressElementCount++)
          {
            esriTopologyParent addressElementParent = addressElementParents.Next();
            long addressFID = addressElementParent.m_FID;
            IFeatureClass addressFC = addressElementParent.m_pFC;

            // Fetch address feature
            IFeature addressFeature = GetFeature(addressFC, addressFID);

            // Get parcel address index
            int addressIndex = addressFeature.Fields.FindField("ADDRESS");

            if (addressIndex >= 0)
            {
              // Get parcel address value
              string addressValue = addressFeature.Value[addressIndex].ToString();

              // Add parcel ID and address value
              parcelWithAddressList.Add($"Parcel ID: {parcelID} / Address Coordinates: {addressValue}");
            }
          }
        }
      }
      return parcelWithAddressList;
    }

    /// <summary>
    ///  Provides a feature class from the feature dataset by the feature class name
    /// </summary>
    /// <param name="featureDataset">Feature dataset object </param>
    /// <param name="featureClassName">Feature class name</param>
    /// <returns>A feature class <see cref="IFeatureClass"/></returns>
    private IFeatureClass GetFeatureClass(IFeatureDataset featureDataset, string featureClassName)
    {
      IFeatureClassContainer featureclassContainer = (IFeatureClassContainer)featureDataset;
      IFeatureClass featureClass = featureclassContainer.ClassByName[featureClassName];

      return featureClass;
    }

    /// <summary>
    /// Provides a feature from the feature class by the feature ObjectId
    /// </summary>
    /// <param name="featureClass">Feature class object </param>
    /// <param name="objectId">ObjectId of the feature </param>
    /// <returns>A feature <see cref="IFeature"/> </returns>
    private IFeature GetFeature(IFeatureClass featureClass, long objectId)
    {
      IQueryFilter queryFilter = new QueryFilterClass
      {
        WhereClause = $"objectId = {objectId}"
      };

      IFeatureCursor featureCursor = featureClass.Search(queryFilter, true);
      IFeature feature = featureCursor.NextFeature();

      return feature;
    }

  }
}
