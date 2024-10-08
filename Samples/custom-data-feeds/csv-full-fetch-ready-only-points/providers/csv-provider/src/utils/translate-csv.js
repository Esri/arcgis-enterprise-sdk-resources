function translate(data, config) {
    const metadata = config.metadata || {};
    const columns = Object.keys(data[0]);
  
    if (metadata.idField && !columns.includes(metadata.idField)) {
      console.warn(`Specified ID field "${metadata.idField}" is not found.`);
    }
  
    return {
      type: "FeatureCollection",
      features: data.map((row) =>
        formatFeature(row, columns, config.geometryColumns, metadata.idField)
      ),
      metadata,
    };
  }
  
  function formatFeature(values, columns, geometryColumns, idField) {
    // Most of what we need to do here is extract the longitude and latitude
    const feature = {
      type: "Feature",
      properties: {},
      geometry: {
        type: "Point",
        coordinates: [],
      },
    };
  
    for (let i = 0; i < columns.length; i++) {
      const value = values[columns[i]];
  
      if (columns[i] === geometryColumns.Longitude) {
        feature.geometry.coordinates.unshift(value);
      } else if (columns[i] === geometryColumns.Latitude) {
        feature.geometry.coordinates.push(value);
      } else if (columns[i] == idField && !isValidId(value)) {
        console.warn(`Invalid ID value: ${value}`);
      } else {
        feature.properties[columns[i]] = value;
      }
    }
  
    if (!isValidGeometry(feature.geometry)) {
      console.warn(
        `Invalid coordinates: ${feature.geometry.coordinates}, setting to [0, 0]`
      );
      feature.geometry.coordinates = [0, 0];
    }
  
    return feature;
  }
  
  // Max ID value supported by feature server:
  // https://koopjs.github.io/docs/usage/provider#setting-provider-metadata-in-getdata
  function isValidId(value) {
    const parsedValue = parseInt(value);
    return 0 <= parsedValue && parsedValue <= 2147483647;
  }
  
  function isValidGeometry(geometry) {
    return (
      !Number.isNaN(geometry.coordinates[0]) &&
      !Number.isNaN(geometry.coordinates[1])
    );
  }
  
  module.exports = translate;
  