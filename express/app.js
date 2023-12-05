const express = require('express');
const path = require('path');
const {Sequelize} = require("sequelize");

const models = require('./db')
const personRouter = require('./routes/person');
const app = express();

app.use(express.json())
app.use('/person', personRouter);


models.sequelize.sync({force: true}).then(function () {
    console.log('connected to database')
    models.PersonSchema.create({
        'name': 'john',
        'surname': 'Doe', 'job': 'IT'
    })
}).catch(function (err) {
    console.log(err)
});


module.exports = app;
