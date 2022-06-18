const express = require('express')
const router = express.Router()

router.get('/', (req, res, next) => {
    res.status(200).json({
        message: 'Handling GET requests to /products'
    })
})

router.post('/', (req, res, next) => {
    const product = {
        name: req.body.name,
        price: req.body.price
    }
    console.log(product)
    res.status(201).json({
        message: 'Handling POST requests to /products',
        product: product
    })
})

router.get('/:productId', (req, res, next) => {
    const id = req.params.productId
    if (id > 0) {
        res.status(200).json({
            message: 'Handling GET requests to /products/' + id,
            id: id
        })
    } else {
        res.status(404).json({
            message: 'Product not found'
        })
    }
})

router.patch('/:productId', (req, res, next) => {
    const id = req.params.productId
    if (id > 0) {
        res.status(200).json({
            message: 'Handling PATCH requests to /products/' + id,
            id: id
        })
    } else {
        res.status(404).json({
            message: 'Product not found'
        })
    }
})

router.delete('/:productId', (req, res, next) => {
    const id = req.params.productId
    if (id > 0) {
        res.status(200).json({
            message: 'Handling DELETE requests to /products/' + id,
            id: id
        })
    } else {
        res.status(404).json({
            message: 'Product not found'
        })
    }
})

module.exports = router