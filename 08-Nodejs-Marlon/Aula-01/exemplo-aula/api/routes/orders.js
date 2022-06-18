const express = require('express')
const router = express.Router()

router.get('/', (req, res, next) => {
    res.status(200).json({
        message: 'Handling GET requests to /orders'
    })
})

router.post('/', (req, res, next) => {
    const order = {
        productId: req.body.productId,
        quantity: req.body.quantity
    }
    console.log(order)
    res.status(201).json({
        message: 'Handling POST requests to /orders',
        order: order
    })
})

router.get('/:orderId', (req, res, next) => {
    const id = req.params.orderId
    if (id > 0) {
        res.status(200).json({
            message: 'Handling GET requests to /orders/' + id,
            id: id
        })
    } else {
        res.status(404).json({
            message: 'Order not found'
        })
    }
})

router.patch('/:orderId', (req, res, next) => {
    const id = req.params.orderId
    if (id > 0) {
        res.status(200).json({
            message: 'Handling PATCH requests to /orders/' + id,
            id: id
        })
    } else {
        res.status(404).json({
            message: 'Order not found'
        })
    }
})

router.delete('/:orderId', (req, res, next) => {
    const id = req.params.orderId
    if (id > 0) {
        res.status(200).json({
            message: 'Handling DELETE requests to /orders/' + id,
            id: id
        })
    } else {
        res.status(404).json({
            message: 'Order not found'
        })
    }
})

module.exports = router