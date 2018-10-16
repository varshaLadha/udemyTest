const Sequelize = require('sequelize');
const {db} = require('./../configs/database.js');
const subCategory = require('./subcategory.schema');

const coursSchema = db.define('Course', {
    id: {
        type: Sequelize.BIGINT,
        autoIncrement: true,
        primaryKey: true
    },
    subCategoryId: {
        type: Sequelize.BIGINT,
        allowNull: false,
        validate: {
            notEmpty: { msg: 'Sub-category Id is required' }
        }
    },
    title: {
        type: Sequelize.STRING,
        allowNull: false,
        validate: {
            notEmpty: { msg: 'Title is required' }
        }
    },
    tag: {
        type: Sequelize.STRING,
        allowNull: true
    },
    instructorName: {
        type: Sequelize.STRING
    },
    rating: {
        type: Sequelize.DOUBLE,
        defaultValue: 0.0
    },
    peopleRated: {
        type: Sequelize.INTEGER,
        defaultValue:0
    },
    averageRating:{
        type: Sequelize.INTEGER,
        defaultValue:0
    },
    cost: {
        type: Sequelize.DOUBLE,
        allowNull: false,
        validate: {
            notEmpty: { msg: 'Cost is required' }
        }
    },
    imageUrl: {
        type: Sequelize.STRING
    },
    isDeleted: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
    }
})

coursSchema.belongsTo(subCategory,{foreignKey: 'subCategoryId'});

coursSchema.sync({force:false}).then((res) => {
    console.log('Course Table Created Successfully');
}).catch((error) => {
    console.log(error);
})

module.exports = coursSchema;