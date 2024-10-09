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
      geojson.metadata = { name: [host, "_in_", id].join("")};
      callback(null, geojson);
    })
    .catch(e => {
      callback(e);
    });
};
module.exports = Model;
