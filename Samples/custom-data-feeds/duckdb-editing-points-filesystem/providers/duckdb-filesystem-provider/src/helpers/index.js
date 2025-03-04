const { normalizeRequestedEdits } = require('./normalize-edits-request');
const { insertRows } = require('./insert-helpers');
const { updateRows } = require('./update-helpers');
const { deleteRows } = require('./delete-helpers');
const { syncWALandDB } = require('./db-helpers');

module.exports = {
  normalizeRequestedEdits,
  insertRows,
  updateRows,
  deleteRows,
  syncWALandDB
};