const Sequelize = require('sequelize');
const {db} = require('./../configs/database.js');
const category = require('./category.schema');

const subcategorySchema = db.define('Subcategory', {
    id: {
        type: Sequelize.BIGINT,
        autoIncrement: true,
        primaryKey: true
    },
    categoryId: {
        type: Sequelize.BIGINT,
        allowNull : false,
        validate: {
            notEmpty: { msg: 'Category id is required field.'},
        }
    },
    subCategoryName: {
        type: Sequelize.STRING,
        allowNull : false,
        validate: {
            notEmpty: { msg: 'sub category name is required field'}
        }
    },
    isDeleted: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
    }
})

subcategorySchema.belongsTo(category,{foreignKey: 'categoryId'});

subcategorySchema.sync({force:false}).then((res) => {
    console.log('Sub-category Table Created Successfully');
}).catch((error) => {
    console.log(error);
})

module.exports = subcategorySchema;

//Finance Entrepreneurship Communications Sales Management Strategy Real_Estate Other Web_Design Graphic_Design
//Interior_Design Fashion Architectural_design Photography_Fundamentals Potraits Video_Design Commercial_Photography
//Arts_And_Crafts Food_And_Beverage Travel Gaming Pet_Care_And_Training Fitness General_Health Sports Nutrition
//Yoga Dance Meditation Dieting Eductional_Development Teaching_Tool Instruments Production Music_Fundamentals
//Vocal English Spanish German French Russian Latin Databases Web_Development Programming_Languages ECommerce
//Digital_Marketing Social_Media_Marketing Branding Public_Relations Advertising Content_Marketing