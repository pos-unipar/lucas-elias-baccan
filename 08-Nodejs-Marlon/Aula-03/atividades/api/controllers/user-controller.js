const jwt = require('jsonwebtoken');

module.exports = {
    verifyJWT: (req, res, next) => {
        const token = req.headers['x-access-token'];
        if (!token) {
            return res.status(401).json({
                auth: false,
                message: 'No token provided'
            });
        }
        jwt.verify(token, 'SECRET', (err, decoded) => {
            if (err) {
                return res.status(401).json({
                    auth: false,
                    message: 'Invalid token'
                });
            }
            req.user_id = decoded._id;
            next();
        });
    },

    get_all_users: async (req, res, next) => {
        try {
            const user = await UserModel.find().select("name username _id");
            res.status(200).json({
                count: user.length,
                users: user.map(user => {
                    return {
                        name: user.name,
                        username: user.username,
                        id: user._id,
                        request: {
                            type: "GET",
                            url: "http://localhost:" + process.env.PORT + "/users/" + user._id
                        }
                    }
                })
            })
        } catch (err) {
            console.log(err);
            res.status(500).json(err);
        }
    }
}