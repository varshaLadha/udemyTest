const Chapter = require('../schema/chapter.schema')

exports.post = (body, done) => {
    Chapter.create(body).
    then((newChapter) =>{
        done(null, newChapter)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}