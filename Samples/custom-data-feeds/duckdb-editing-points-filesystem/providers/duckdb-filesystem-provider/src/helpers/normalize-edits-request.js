function normalizeRequestedEdits(body) {
    let edits = {};
    // if it service-level, just return the object
    if(body.edits) { 
      return {
        edits: body.edits,
        editLevel : 'service'
      }
    // handle the layer level request
    } else {
      if(body.adds) edits.adds = body.adds;
      if(body.updates) edits.updates = body.updates;
      if(body.deletes) {
        // If body.deletes is already an array, use it directly
        // Otherwise, split it by commas and convert to an array
        let deletesArray = Array.isArray(body.deletes) 
                            ? body.deletes 
                            : String(body.deletes)
                              .split(',')
                              .map(item => Number(item.trim()));
        edits.deletes = deletesArray;
      }
      return {
        edits: [edits],
        editLevel: 'layer'
      }
    }
}

module.exports = { normalizeRequestedEdits };