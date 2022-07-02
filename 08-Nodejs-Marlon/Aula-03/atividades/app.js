const express = require('express')
const morgan = require('morgan')
const bodyParser = require('body-parser')
const mongoose = require('mongoose')

mongoose.connect(process.env.MONGOOSE_URI,
    {
        useNewUrlParser: true,
        useUnifiedTopology: true,
    }, err => {
        if (err) throw err;
        console.log('Connected to MongoDB')
    }
)

require('./api/models/product')
require('./api/models/order')

const app = express()

const productsRputes = require('./api/routes/products')
const ordersRoutes = require('./api/routes/orders')

app.use(morgan('dev'))

app.use('/uploads', express.static('uploads'))

app.use(bodyParser.urlencoded({ extended: false }))
app.use(bodyParser.json())

const cors = (req, res, next) => {
    const whiteList = [
        'http://localhost:3000',
        'http://localhost:3001'
    ]
    let origin = req.headers.origin
    if (whiteList.indexOf(origin) > -1) {
        res.setHeader('Access-Control-Allow-Origin', origin)
    }
    res.setHeader('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept, Authorization')
    res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PATCH, DELETE, OPTIONS')
    res.setHeader('Access-Control-Allow-Credentials', true)
    next()
}

app.use(cors)

app.use('/products', productsRputes)
app.use('/orders', ordersRoutes)

app.use('/api', (req, res, next) => {
    res.status(200).json({
        message: 'Hello word!'
    })
})

app.use((req, res, next) => {
    const error = new Error('Not found')
    error.status = 404
    next(error)
})

app.use((error, req, res, next) => {
    res.status(error.status || 500)
    res.json({
        error: {
            message: error.message
        }
    })
})

module.exports = app