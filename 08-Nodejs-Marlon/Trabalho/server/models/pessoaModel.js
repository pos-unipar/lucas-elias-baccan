const mongoose = require('mongoose');

const schema = new mongoose.Schema({
    nome: { type: String, required: true },
    sobrenome: { type: String },
    telefone: { type: String },
    email: { type: String },
    status: { type: String, default: "Ativo" }
},
    {
        timestamps: true
    }
);

mongoose.model('Pessoa', schema);