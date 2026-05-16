function Controller(model) {
    this.model = model;
}

Controller.prototype.editData = async function (req, res) {
    try {
        const reply = await this.model.editData(req);
        res.status(200).json(reply);
    } catch (error) {
        res.status(200).json({
            error: {
                code: 1000,
                message: error.message,
                details: []
            }
        });
    }
};

module.exports = Controller;
