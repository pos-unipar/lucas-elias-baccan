const express = require('express')
const { default: mongoose } = require('mongoose')
const router = express.Router()

const OrderModel = mongoose.model('Order')
const ProductModel = mongoose.model('Product')

router.get('/', async (req, res, next) => {
    try {
        const orders = await OrderModel.find()
        res.status(200).json({
            message: 'Handling GET requests to /orders',
            count: orders.length,
            orders: orders.map(order => {
                return {
                    product: order.product,
                    quantity: order.quantity,
                    _id: order._id,
                    request: {
                        type: 'GET',
                        url: 'http://localhost:' + process.env.PORT + '/orders/' + order._id
                    }
                }
            }),
        })
    } catch (error) {
        res.status(500).json(error)
    }
})

router.post('/', async (req, res, next) => {
    try {
        let product = null;
        try {
            product = await ProductModel.findById(req.body.product)
            if (!product) {
                res.status(404).json({
                    message: 'Product not found'
                })
                return;
            }
        } catch (error) {
            res.status(500).json(error)
        }

        let order = new OrderModel({
            product: product._id,
            quantity: req.body.quantity
        })

        order = await order.save()

        console.log(order)
        res.status(201).json({
            message: 'Handling POST requests to /orders',
            order: order
        })
    } catch (error) {
        res.status(500).json(error)
    }
})

router.get('/:orderId', async (req, res, next) => {
    const id = req.params.orderId
    let order = null
    try {
        console.log("teste")
        order = await OrderModel.find({ _id: id }).populate('product', '_id name price')
        if (order) {
            res.status(200).json({
                message: 'Handling GET requests to /orders/:orderId',
                order: order
            })
        } else {
            res.status(404).json({
                message: 'Order not found'
            })
        }
    } catch (error) {
        res.status(500).json(error)
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

router.delete('/:orderId', async (req, res, next) => {
    const id = req.params.orderId
    try {
        const status = await OrderModel.deleteOne({ _id: id })
        res.status(200).json({
            message: 'Handling DELETE requests to /products/' + id,
            id: id,
            status: status
        })
    } catch (error) {
        res.status(500).json(error)
    }
})

module.exports = router