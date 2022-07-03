const swaggerJSDoc = require('swagger-jsdoc');

const swaggerDefinition = {
    info: {
        title: 'Aula Node Pos Graduacao',
        version: '1.0.0',
        description: 'API documentation',
    },
    host: 'localhost:4000',
    basePath: '/',
    schemes: ['http'],
};

const options = {
    swaggerDefinition,
    apis: ['./api/docs/**/*.yaml'],
};

module.exports = swaggerJSDoc(options);