const express = require('express')
const { default: mongoose } = require('mongoose')
const router = express.Router()
const multer = require('multer')
const storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'uploads/')
    },
    filename: function (req, file, cb) {
        cb(null, new Date().getMilliseconds() + file.originalname)
    }
})
const upload = multer({ storage: storage })

const ProductModel = mongoose.model('Product')

router.get('/', async (req, res, next) => {
    try {
        const products = await ProductModel.find().select('name price _id')
        res.status(200).json({
            message: 'Handling GET requests to /products',
            count: products.length,
            products: products.map(product => {
                return {
                    name: product.name,
                    price: product.price,
                    _id: product._id,
                    request: {
                        type: 'GET',
                        url: 'http://localhost:3000/products/' + product._id
                    }
                }
            })
        })
    } catch (error) {
        next(error)
    }
})

router.post('/', upload.single('productImage'), async (req, res, next) => {
    try {
        const product = new ProductModel({
            name: req.body.name,
            price: req.body.price,
            image: req.file.path
        })
        await product.save()
        res.status(201).json({
            message: 'Handling POST requests to /products',
            product: product
        })
    } catch (err) {
        console.log(err)
        res.status(500).json(err)
    }
})

router.get('/:productId', async (req, res, next) => {
    try {
        const id = req.params.productId
        const product = await ProductModel.findOne({ _id: id }).select('name price _id')
        if (product) {
            res.status(200).json({
                message: 'Handling GET requests to /products/' + id,
                count: 1,
                id: id,
                product: product
            })
        } else {
            res.status(404).json({
                message: 'Product not found'
            })
        }
    } catch (error) {
        res.status(500).json(error)
    }
})

router.patch('/:productId', async (req, res, next) => {
    const id = req.params.productId

    const updateCampos = {}
    Object.entries(req.body).forEach(([key, value]) => {
        updateCampos[key] = value
    })

    try {
        let status = await ProductModel.updateOne(
            { _id: id },
            { $set: updateCampos }
        )

        res.status(200).json({
            message: 'Handling PATCH requests to /products/' + id,
            id: id,
            status: status
        })
    } catch (error) {
        res.status(500).json(error)
    }
})

router.delete('/:productId', async (req, res, next) => {
    const id = req.params.productId
    try {
        const status = await ProductModel.deleteOne({ _id: id })
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