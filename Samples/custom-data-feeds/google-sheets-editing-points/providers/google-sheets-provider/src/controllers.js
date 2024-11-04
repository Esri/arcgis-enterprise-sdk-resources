function Controller (model) {
    this.model = model
}

Controller.prototype.editData = async function (req, res) {
    const pathParams = req.params;
    const body = req.body;
    const reply = await this.model.editData(pathParams, body);
    res.status(200).json(reply)
}

module.exports = Controller