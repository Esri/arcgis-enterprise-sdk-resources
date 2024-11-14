module.exports = (data) => {
    if (!Array.isArray(data)) {
        data = [data];
    }
    return {
        type: 'FeatureCollection',
        features: data.map((specimen) => {
            if(!specimen.decimalLatitude) {
                specimen.decimalLatitude = 0.0;
                specimen.decimalLongitude = 0.0;
            }
            if(!specimen.elevation) {
                specimen.elevation = 0.0;
            }
            return {
                type: 'Feature',
                properties: {
                    gbifID: specimen.key,
                    scientificName: specimen.scientificName ?? '',
                    genus: specimen.genus ?? '',
                    species: specimen.species ?? '',
                    basisOfRecord: specimen.basisOfRecord ?? '',
                    continent: specimen.continent ?? '',
                    country: specimen.country ?? '',
                    stateProvince: specimen.stateProvince ?? '',
                    locality: specimen.verbatimLocality ?? '',
                    elevation: specimen.elevation ?? null, // Assuming elevation can be null if not present
                    eventDate: specimen.eventDate ?? '',
                    year: specimen.year ?? null, // Assuming year can be null if not present
                    month: specimen.month ?? null, // Assuming month can be null if not present
                    day: specimen.day ?? null, // Assuming day can be null if not present
                    image: (specimen.media?.length > 0) ? specimen.media[0].identifier : ''
                },
                geometry: {
                    type: 'Point',
                    coordinates: [
                        specimen.decimalLongitude || 0.00,
                        specimen.decimalLatitude || 0.00,
                        specimen.elevation || 0.00
                    ]
                }
            }
        }),
    };
};