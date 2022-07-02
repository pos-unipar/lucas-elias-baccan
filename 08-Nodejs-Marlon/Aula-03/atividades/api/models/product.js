const mongoose = require('mongoose')

const productsSchema = new mongoose.Schema({
    name: {
        type: String
    },
    price: {
        type: Number
    }
}, {
    timestamps: true
})

mongoose.model('Product', productsSchema)