const Course= require('./../schema/course.schema')
const {courseAttributes} = require('./../configs/general')

exports.getBySubCategoryId = (subCategoryId, done) => {
    Course.findAll({
        where: {
            subCategoryId: subCategoryId,
            isDeleted: false
        },
        raw: true,
        attributes: courseAttributes
    }).then((data) => {
        done(null, data)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}

exports.post = (body, done) => {
    Course.create(body).
    then((newCourse) => {
        done(null, newCourse)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}