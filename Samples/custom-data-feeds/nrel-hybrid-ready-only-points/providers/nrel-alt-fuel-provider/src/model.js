const config = require('config');
class Model {
    
    constructor() {
        this.lastFetchTime = 0; // Initialize last fetch time to 0
        this.allTheData = [];
    }

    async getData() {
        
        const states = config.altFuelStations.states; // get the list of states from our config
        const currentTime = Date.now();
        const timeElapsed = currentTime - this.lastFetchTime;
        const shouldFetch = timeElapsed > 86400000; // 24 hours in milliseconds
        
        if (shouldFetch) {

            this.allTheData = []; // clear the contents of the cache when we need to fetch again

            for (const state of states) {  // loop through all the states
                try {
                    const response = await fetch(`${config.altFuelStations.afsURL}/v1.json?api_key=${config.altFuelStations.apiKey}&state=${state}`);
                    
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }

                    const data = await response.json();
                    this.allTheData.push(...data.fuel_stations); // store each state array into the main array
                } catch (error) {
                    logger.error(`Failed to fetch data for state ${state}:`, error);
                }
            }

            this.lastFetchTime = Date.now(); // Update last fetch time
        } else {
            console.log("Using cached data.");
        }

        let geojson = convertToGeoJSON(this.allTheData);

        return {
            ...geojson,
            metadata: {
                idField: "id", // use the id field as the ObjectID
                maxRecordCount: 5000
            }
        };
    }
}

function convertToGeoJSON(fuelStations) {
    // Initialize the GeoJSON structure
    const geoJSON = {
        type: "FeatureCollection",
        features: []
    };

    // Iterate over each fuel station
    fuelStations.forEach(station => {
        // Create a feature for each station using only a select number of the attributes
        const feature = {
            type: "Feature",
            properties: {
                fuel_type_code: station.fuel_type_code,
                id: station.id,
                facility_type: station.facility_type,
                station_name: station.station_name,
                station_phone: station.station_phone,
                city: station.city,
                state: station.state,
                street_address: station.street_address,
            },
            geometry: {
                type: "Point",
                coordinates: [station.longitude, station.latitude] 
            }
        };

        geoJSON.features.push(feature);
    });

    return geoJSON;
}

module.exports = Model
