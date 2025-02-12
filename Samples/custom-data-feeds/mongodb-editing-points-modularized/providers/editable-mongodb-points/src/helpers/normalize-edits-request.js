function normalizeRequestedEdits(body) {
    let edits = {};
    // if it service-level, just return the object
    if(body.edits) { 
    
      return {
        edits: body.edits[0],
        editLevel : 'service',
        layer: body.edits[0].id
      }
    // handle the layer level request
    } else {
      if(body.adds) edits.adds = body.adds;
      if(body.updates) edits.updates = body.updates;
      if(body.deletes) {
        let deletesArray = [body.deletes];
        edits.deletes = deletesArray;
      }
      return {
        edits: edits,
        editLevel: 'layer'
      }
    }
}

module.exports = { normalizeRequestedEdits };