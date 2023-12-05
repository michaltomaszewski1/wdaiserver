const express = require('express');
const {PersonSchema} = require("../db");
const {json} = require("sequelize");
const router = express.Router();

router.get('', async (req, res, next) => {
    const people = await PersonSchema.findAll();
    res.status(200).json(people);

})

router.get('/:id', async (req, res, next) => {
    const person = await PersonSchema.findByPk(req.params.id);
    res.status(200).json(person);
})

router.post('/create', async (req, res, next) => {
    const newPerson = await PersonSchema.create(req.body)
    res.status(201).json(newPerson);
})

module.exports = router;