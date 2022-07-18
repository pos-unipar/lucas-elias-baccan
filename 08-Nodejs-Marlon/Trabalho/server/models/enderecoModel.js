const mongoose = require('mongoose');

const schema = new mongoose.Schema({
    pessoa_id: { type: mongoose.Schema.Types.ObjectId, ref: 'Pessoa' },
    cep: { type: String, required: true, maxlength: 8 },
    logradouro: { type: String },
    numero: { type: String },
    complemento: { type: String },
    bairro: { type: String },
    cidade: { type: String },
    estado: { type: String },
},
    {
        timestamps: true
    }
);

mongoose.model('Endereço', schema);