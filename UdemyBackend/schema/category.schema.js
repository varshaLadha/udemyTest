const Sequelize = require('sequelize');
const {db} = require('./../configs/database.js');

const categorySchema = db.define('Category', {
    id:{
        type: Sequelize.BIGINT,
        autoIncrement: true,
        primaryKey: true
    },
    categoryName: {
        type: Sequelize.STRING,
        allowNull : false,
        unique: true,
        validate: {
            notEmpty: { msg: 'category name is required field'}
        }
    },
    imageUrl:{
        type: Sequelize.STRING
    },
    isDeleted:{
        type:Sequelize.BOOLEAN,
        defaultValue: false,
    }
});

categorySchema.sync({force:false}).then((res) => {
    console.log('Category Table Created Successfully');
}).catch((error) => {
    console.log(error);
})

module.exports = categorySchema;