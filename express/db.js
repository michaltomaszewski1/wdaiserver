const {Sequelize, DataTypes} = require("sequelize");

const sequelize = new Sequelize('test', 'root', '', {
    host: 'localhost',
    dialect: 'sqlite',
    operatorsAliases: false,
    storage: './data/database.sqlite',
    define: {
        timestamps: false
    }
})
const PersonSchema = sequelize.define("Person", {
        id: {
            type: DataTypes.INTEGER, autoIncrement: true,
            primaryKey: true
        },
        name: DataTypes.STRING,
        surname: DataTypes.STRING,
        job: DataTypes.STRING,
    }, {}
);

// (async () => {
//     await sequelize.sync({ force: true }); // Drops all tables and recreates them
//     // Additional logic to seed initial data if needed
//     console.log('Database reset complete');
// })();

module.exports = {sequelize, PersonSchema}