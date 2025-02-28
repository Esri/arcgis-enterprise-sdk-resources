const { normalizeRequestedEdits } = require('./normalize-edits-request');
const { insertRows } = require('./insert-helpers');

module.exports = {
  normalizeRequestedEdits,
  insertRows
};