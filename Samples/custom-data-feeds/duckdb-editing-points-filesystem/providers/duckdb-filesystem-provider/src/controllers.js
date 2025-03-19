function Controller (model) {
    this.model = model
}

Controller.prototype.editData = async function (req, res) {
    const reply = await this.model.editData(req);
    res.status(200).json(reply)
}

module.exports = Controller