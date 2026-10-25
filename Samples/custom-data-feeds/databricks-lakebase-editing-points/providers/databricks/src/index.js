
const packageInfo = require('../package.json')
const csconfigInfo = require('../cdconfig.json')

const provider = {
  type: csconfigInfo.type,
  name: csconfigInfo.name,
  version: packageInfo.version,
  Model: require('./model')
}

module.exports = provider
