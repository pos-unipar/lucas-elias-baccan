var mysql = require('mysql');

var connection = mysql.createConnection({
    host: 'mysql',
    user: 'db_mhs',
    password: 'db_mhs',
    database: 'db_mhs'
});

connection.connect(function (err) {
    // connection.end();
    if (!err) {
        console.log("Database is connected ... \n\n");
    } else {
        console.log("Error connecting database ... \n\n");
    }
});

module.exports = connection;