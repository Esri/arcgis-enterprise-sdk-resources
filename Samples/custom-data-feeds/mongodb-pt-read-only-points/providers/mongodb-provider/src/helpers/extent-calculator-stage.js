function extentCalculatorStage(geometryField) {
  // console.log(geometryField);
  // console.log(coordinates);
  return {
    alternateID: null,
    result: {
      $accumulator: {
        init: `function () {
          return {
            extent: { xmin: null, xmax: null, ymin: null, ymax: null },
            count: 0,
          };
        }`,
        accumulate: `function (state, coordinates) {
          const getMax = (max, current) => {
            return max? Math.max(max, current) : current;
          };
          const getMin = (min, current) => {
            return min? Math.min(min, current) : current;
          };
          const reduceCoordsToExtent = (extent, coords) => {
            const {xmin, xmax, ymin, ymax } = extent;
            if (!Array.isArray(coords[0])) {
              extent.xmin = getMin(xmin, coords[0]);
              extent.ymin = getMin(ymin, coords[1]);
              extent.xmax = getMax(xmax, coords[0]);
              extent.ymax = getMax(ymax, coords[1]);
              return extent;
            }

            return coords.reduce(reduceCoordsToExtent, extent);
          };

          state.count = state.count + 1;
          state.extent = reduceCoordsToExtent(state.extent, coordinates);
          return state;
        }`,
        accumulateArgs: [`$${geometryField}.coordinates`],
        merge: `function () {}`,
        lang: 'js',
      },
    },
  };
}

module.exports = { extentCalculatorStage };
