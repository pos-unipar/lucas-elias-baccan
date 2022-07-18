const express = require('express');
const morgan = require('morgan');
const bodyParser = require('body-parser');
const mongoose = require('mongoose')
const app = express();

mongoose.connect(process.env.MONGODB_URL,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true
    }, err => {
        if (err) throw err;
        console.log('Connected to MongoDB!!!')
    }
);

app.use(morgan('dev')); // Faz o log das rotas e métodos
app.use(bodyParser.urlencoded({ extended: false })); // Parseia o body da requisição
app.use(bodyParser.json()); 

require('./models/pessoaModel')
app.use('/api/pessoas', require('./routes/pessoaRoute'));
require('./models/enderecoModel')
app.use('/api/enderecos', require('./routes/enderecoRoute'));


app.get('/', (req, res, next) => {
    res.status(200).json({
        message: 'Lucas Elias Baccan - RA: 133493'
    })
})

// Rota para raiz da API
app.use('/api', (req, res, next) => {
    res.status(200).json({
        message: 'API está funcionando'
    })
})

// Erros de paginas nao encontradas
app.use((req, res, next) => {
    const error = new Error('Not Found')
    error.status = 404;
    next(error);
});

// Erros internos do servidor
app.use((error, req, res, next) => {
    res.status(error.status || 500);
    res.json({
        error: {
            message: error.message
        }
    })
});

module.exports = app;