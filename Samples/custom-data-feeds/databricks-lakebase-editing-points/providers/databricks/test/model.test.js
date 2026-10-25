/* eslint-env mocha */

const chai = require('chai')
const expect = chai.expect

describe('model', function () {
  it('should get a geojson from the getData() function', (done) => {
    const Model = require('../src/model')
    const model = new Model()

    model.getData({}, (err, geojson) => {
      expect(err).to.equal(null)

      expect(geojson.type).to.equal('FeatureCollection')
      expect(geojson.features).to.be.an('array')
      done()
    })
  })
})
