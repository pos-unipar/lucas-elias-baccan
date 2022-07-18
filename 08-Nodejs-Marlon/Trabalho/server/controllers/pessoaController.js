const mongoose = require('mongoose');
const model = mongoose.model('Pessoa');

module.exports = {
    async get(req, res, next) {
        const pessoa = await model.find()
            .select('nome email telefone');
        res.status(200).json({
            size: pessoa.length,
            result: pessoa.map(pessoa => {
                return {
                    pessoa,
                    url: `${process.env.URL}:${process.env.PORT}/api/pessoas/${pessoa._id}`
                };
            })
        });
    },
    async getById(req, res, next) {
        const pessoa = await model.findOne({ _id: req.params.id });
        res.status(200).json({
            result: pessoa,
            url:`${process.env.URL}:${process.env.PORT}/api/pessoas/${pessoa._id}`
        });
    },
    async post(req, res, next) {
        let pessoa = new model(req.body);
        try {
            pessoa = await pessoa.save();
            res.status(201).json({
                result: pessoa,
                url:`${process.env.URL}:${process.env.PORT}/api/pessoas/${pessoa._id}`
            });
        } catch (error) {
            if (error.name === "ValidationError") {
                res.status(400).json({ message: error.message });
            }
        }
    },
    async patch(req, res, next) {
        const updateCampos = {};
        Object.entries(req.body).map(item => {
            console.log(item);
            updateCampos[item[0]] = item[1];
        })
        const pessoa = await model.findOneAndUpdate({ _id: req.params.id }, updateCampos, { new: true });
        res.status(200).json({
            result: pessoa,
            url:`${process.env.URL}:${process.env.PORT}/api/pessoas/${pessoa._id}`
        });
    },
    async delete(req, res, next) {
        const enderecos = await mongoose.model('Endereço').find({ pessoa_id: req.params.id });
        enderecos.map(endereco => {
            mongoose.model('Endereço').findByIdAndRemove(endereco._id);
        });
        await model.findByIdAndRemove(req.params.id);
        res.status(200).json({ message: 'Pessoa removida com sucesso' });
    }
}