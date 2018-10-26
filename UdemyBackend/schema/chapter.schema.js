const Sequelize = require('sequelize');
const {db} = require('./../configs/database.js');
const courseDetail = require('./courseDetail.schema')

const chapterSchema = db.define('Chapter', {
    id: {
        type: Sequelize.BIGINT,
        primaryKey: true,
        autoIncrement: true
    },
    courseDetailId: {
        type: Sequelize.BIGINT,
        allowNull: false,
        validate: {
            notEmpty: {msg: 'Course Detail Id is required'}
        }
    },
    title: {
        type: Sequelize.STRING,
        allowNull: false,
        validate: {
            notEmpty: {msg: 'Title is required'}
        }
    },
    isDeleted:{
        type:Sequelize.BOOLEAN,
        defaultValue: false,
    }
})

chapterSchema.belongsTo(courseDetail, {foreignKey: 'courseDetailId'})

chapterSchema.sync({force: false}).then((res) => {
    console.log('Chapter table created successfully')
}).catch((err) => {
    console.log(err)
})

module.exports = chapterSchema