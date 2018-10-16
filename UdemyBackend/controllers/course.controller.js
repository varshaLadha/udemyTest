const Course= require('./../schema/course.schema')
const {courseAttributes} = require('./../configs/general')
const {db} = require('./../configs/database')

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
    body.averageRating = body.rating/(body.peopleRated*5)
    Course.create(body).
    then((newCourse) => {
        done(null, newCourse)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}

exports.popularCoursesByCategoryId = (categoryId, done) => {
    db.query("SELECT * from Courses WHERE subCategoryId IN (Select id from Subcategories where categoryId = "+categoryId+") ORDER BY averageRating LIMIT 8").
    spread((data) =>{
        done(null, data)
    }).catch((err) =>{
        done({error: err.message, detail:err})
    })
}

exports.popularCourses = (done) => {
    Course.findAll({
        where:{
            isDeleted: false
        },
        order: [
            ['averageRating','DESC']
        ]
    }).then((data) => {
        done(null, data)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}
