const CourseDetail= require('./../schema/courseDetail.schema')
const {courseDetailAttributes} = require('./../configs/general')

exports.getByCourseId = (courseId, done) => {
    CourseDetail.find({
        where: {
            courseId: courseId,
            isDeleted: false
        },
        raw: true,
        attributes: courseDetailAttributes
    }).then((data) => {
        done(null, data)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}

exports.post = (body, done) => {
    CourseDetail.create(body).
    then((newCourseDetail) => {
        done(null, newCourseDetail)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}