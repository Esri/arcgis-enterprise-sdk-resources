const config = require("../config/default.json");
const parseGeoJSON = require("./parseGeoJSON");
const yelp = require("yelp-fusion");
const yelpClient = yelp.client(config.yelp.apiKey);
function Model(koop) {}
Model.prototype.getData = function(req, callback) {
  const host = req.params.host;
  const id = req.params.id;
  yelpClient
    .search({
      categories: host,
      location: id
    })
    .then(response => {
      geojson = parseGeoJSON(response.jsonBody);
      geojson.metadata = { 
        name: [host, "_in_", id].join(""),
        labelingInfo: [
          {
            "symbol": {
              "kerning": true,
              "color": [214, 75, 75, 255],
              "yoffset": 0,
              "xoffset": 0,
              "haloColor": [211, 211, 211, 255],
              "rotated": false,
              "type": "esriTS",
              "horizontalAlignment": "center",
              "haloSize": 1,
              "angle": 0,
              "text": "",
              "verticalAlignment": "baseline",
              "font": {
                "size": 12.75,
                "family": "Arial"
              }
            },
            "maxScale": 0,
            "repeatLabel": true,
            "where": "category = 'Cocktail Bars'",
            "minScale": 0,
            "labelExpressionInfo": {
              "expression": "$feature[\"category\"]"
            },
            "labelExpression": "[category]",
            "labelPlacement": "esriServerPolygonPlacementAlwaysHorizontal"
          }
        ],
        renderer: {
          "type": "simple",
          "symbol": {
            "type": "esriSMS",
            "style": "esriSMSCircle",
            "color": [255, 0, 0, 255],
            "size": 10,
            "angle": 0,
            "xoffset": 0,
            "yoffset": 0,
            "outline": {
              "color": [0, 0, 0, 255],
              "width": 1
            }
          },
          "label": "",
          "description": "",
          "rotationType": "geographic",
          "rotationExpression": "[Rotation] * 2"
        }
      };
      callback(null, geojson);
    })
    .catch(e => {
      callback(e);
    });
};
module.exports = Model;
