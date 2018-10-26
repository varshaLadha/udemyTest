const Sequelize = require('sequelize');
const {db} = require('./../configs/database.js');
const chapter = require('./chapter.schema')

const chapterDetailSchema = db.define('ChapterDetail',{
    id: {
        type: Sequelize.BIGINT,
        primaryKey: true,
        autoIncrement: true
    },
    chapterId: {
        type: Sequelize.BIGINT,
        allowNull: false,
        validate: {
            notEmpty: {msg: 'Chapter Id is required'}
        }
    },
    title: {
        type: Sequelize.STRING,
        allowNull: false,
        validate: {
            notEmpty: {msg: 'Title is required'}
        }
    },
    videoURL:{
        type: Sequelize.STRING
    },
    videoDuration:{
        type:Sequelize.STRING
    },
    previewAvailable:{
        type:Sequelize.BOOLEAN,
        defaultValue: false,
    },
    isDeleted:{
        type:Sequelize.BOOLEAN,
        defaultValue: false,
    }
})

chapterDetailSchema.belongsTo(chapterDetailSchema, {foreignKey: 'chapterId'})

chapterDetailSchema.sync({force: false}).then((res) => {
    console.log('Chapter detail table created successfully')
}).catch((err) => {
    console.log(err)
})

module.exports = chapterDetailSchema