const { normalizeRequestedEdits } = require('./normalize-edits-request');
const { insertRows } = require('./insert-helpers');
const { updateRows } = require('./update-helpers');

module.exports = {
  normalizeRequestedEdits,
  insertRows,
  updateRows,
};