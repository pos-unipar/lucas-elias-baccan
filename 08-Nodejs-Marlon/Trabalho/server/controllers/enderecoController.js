const mongoose = require('mongoose');
const model = mongoose.model('Endereço');

module.exports = {
    async get(req, res, next) {
        const endereco = await model.find()
            .select('pessoa_id cep cidade uf');
        res.status(200).json({
            size: endereco.length,
            result: endereco.map(endereco => {
                return {
                    endereco,
                    url: `${process.env.URL}:${process.env.PORT}/api/enderecos/${endereco._id}`
                };
            })
        });
    },
    async getById(req, res, next) {
        const endereco = await model.findOne({ _id: req.params.id });
        res.status(200).json({
            result: endereco,
            url:`${process.env.URL}:${process.env.PORT}/api/enderecos/${endereco._id}`
        });
    },
    async post(req, res, next) {
        let endereco = new model(req.body);
        let pessoa = await mongoose.model('Pessoa').findOne({ _id: req.body.pessoa_id });
        if (!pessoa) {
            res.status(404).json({ message: 'Pessoa não encontrada' });
        }
        try {
            endereco = await endereco.save();
            res.status(201).json({
                result: endereco,
                url:`${process.env.URL}:${process.env.PORT}/api/enderecos/${endereco._id}`
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
        const endereco = await model.findOneAndUpdate({ _id: req.params.id }, updateCampos, { new: true });
        res.status(200).json({
            result: endereco,
            url:`${process.env.URL}:${process.env.PORT}/api/enderecos/${endereco._id}`
        });
    },
    async delete(req, res, next) {
        const enderecos = await mongoose.model('Endereço').find({ pessoa_id: req.params.id });
        enderecos.map(endereco => {
            mongoose.model('Endereço').findByIdAndRemove(endereco._id);
        });
        await model.findByIdAndRemove(req.params.id);
        res.status(200).json({ message: 'Endereço removido com sucesso' });
    }
}