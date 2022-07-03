const express = require('express');
const router = express.Router();
const mongoose = require('mongoose');
const passport = require('passport');

const UserModel = mongoose.model('User');

const controllerUser = require('../controllers/user-controller');

router.get('/', controllerUser.verifyJWT, controllerUser.get_all_users);


router.get('/:userId', async (req, res, next) => {
    const id = req.params.userId;

    try {
        const user = await UserModel.findOne({ _id: id });
        if (user) {
            res.status(200).json({
                user: user,
                request: {
                    type: "GET",
                    url: "http://localhost:" + process.env.PORT + "/users/" + user._id
                }
            });
        } else {
            res.status(404).json("Usuários não existe!");
        }
    } catch (err) {
        console.log(err);
        res.status(500).json(err);
    }
});

router.post('/signup', async (req, res, next) => {
    try {
        let user = new UserModel({
            name: req.body.name,
            username: req.body.username,
            // password: req.body.password,
            acesso: req.body.acesso
        });
        user.setPassword(req.body.password);

        user = await user.save();
        res.status(201).json({
            message: 'Usuário criada com sucesso!',
            createdUser: {
                name: user.name,
                username: user.username,
                id: user._id,
                request: {
                    type: "GET",
                    url: "http://localhost:" + process.env.PORT + "/users/" + user._id
                }
            }
        })
    } catch (err) {
        console.log(err);
        res.status(500).json(err);
    }
});

router.post('/login', function (req, res, next) {
    if (!req.body.username || !req.body.password) {
        return res.status(400).json({ message: 'Por favor, preencha todos os campos' });
    }

    passport.authenticate('local', function (err, user, info) {
        if (err) { return next(err); }

        if (user) {
            return res.json({
                token: user.generateJWT(),
                user: user
            });
        } else {
            return res.status(401).json(info);
        }
    })(req, res, next);
});

router.patch('/:userId', async (req, res, next) => {
    const id = req.params.userId;
    const updateCampos = {};
    Object.entries(req.body).map(item => {
        console.log(item);
        updateCampos[item[0]] = item[1];
    })
    try {
        let status = await UserModel.updateOne({ _id: id }, { $set: updateCampos });

        res.status(200).json({
            message: 'Update users',
            status: status,
            request: {
                type: "GET",
                url: "http://localhost:" + process.env.PORT + "/users/" + id
            }
        })
    } catch (err) {
        console.log(err);
        res.status(500).json(err);
    }
})

router.delete('/:userId', async (req, res, next) => {
    const id = req.params.userId;

    try {
        let status = await UserModel.deleteOne({ _id: id });

        res.status(200).json({
            message: 'Usuário deletado',
            status: status
        })
    } catch (err) {
        console.log(err);
        res.status(500).json(err);
    }
})

module.exports = router;