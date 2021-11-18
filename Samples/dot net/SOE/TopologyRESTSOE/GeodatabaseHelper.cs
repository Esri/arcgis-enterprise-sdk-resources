using ESRI.ArcGIS.Geodatabase;
using System;

namespace TopologyRESTSOE
{
  /// <summary>
  /// Class contains geodatabase related helper methods
  /// </summary>
  public class GeodatabaseHelper
  {
    public IWorkspace GetGeodatabaseWorkspace(string gdbPath)
    {
      Type factoryType = Type.GetTypeFromProgID("esriDataSourcesGDB.FileGDBWorkspaceFactory");
      IWorkspaceFactory workspaceFactory = (IWorkspaceFactory)Activator.CreateInstance(factoryType);

      return workspaceFactory.OpenFromFile(gdbPath, 0);
    }

    public IFeatureDataset GetFeatureDatasetFromWorkspace(IWorkspace workspace, string featureDatasetName)
    {
      IFeatureWorkspace featureWorkspace = workspace as IFeatureWorkspace;
      IFeatureDataset featureDataset = featureWorkspace?.OpenFeatureDataset(featureDatasetName);

      return featureDataset;
    }
  }
}
