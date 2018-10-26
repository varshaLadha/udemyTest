const ChapterDetail = require('../schema/chapterDetail.schema')

exports.post = (body, done) => {
    ChapterDetail.create(body).
    then((newUnit) => {
        done(null, newUnit)
    }).
    catch((err) =>{
        done({error: err.message, detail:err})
    })
}