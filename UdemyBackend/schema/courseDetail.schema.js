const Sequelize = require('sequelize');
const {db} = require('./../configs/database.js');
const course = require('./course.schema');

const courseDetailSchema = db.define('CourseDetail',{
    id: {
        type: Sequelize.BIGINT,
        primaryKey: true,
        autoIncrement: true
    },
    courseId: {
        type: Sequelize.BIGINT,
        allowNull: false,
        validate: {
            notEmpty: {msg: 'Course id is required'}
        }
    },
    shortDescription: {
        type: Sequelize.STRING
    },
    description: {
      type: Sequelize.STRING
    },
    totalLearningHours: {
        type: Sequelize.STRING,
        allowNull: false
    },
    learning: {
        type: Sequelize.STRING,
        allowNull: false
    },
    requirements: {
        type: Sequelize.STRING
    },
    totalLectures: {
        type: Sequelize.INTEGER
    },
    previewVideoURL: {
        type: Sequelize.STRING
    },
    quiz: {
        type: Sequelize.INTEGER,
        defaultValue: 0
    },
    articles: {
        type: Sequelize.INTEGER,
        defaultValue:0
    },
    assignments: {
        type: Sequelize.INTEGER,
        defaultValue: 0
    },
    lifeTimeAccess: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
    },
    access: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
    },
    certification: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
    },
    supportFiles: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
    },
    isDeleted: {
        type: Sequelize.BOOLEAN,
        defaultValue: false
    }
})

courseDetailSchema.belongsTo(course, {foreignKey: 'courseId'})

courseDetailSchema.sync({force:false}).then((res) => {
    console.log('Course detail Table Created Successfully');
}).catch((error) => {
    console.log(error);
})

module.exports = courseDetailSchema;