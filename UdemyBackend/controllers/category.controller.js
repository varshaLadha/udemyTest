const Category = require('./../schema/category.schema')
const {categoryAttributes} = require('./../configs/general')

exports.getAll = (done) => {
    Category.findAll({
        where:{
            isDeleted: false
        },
        raw: true,
        attributes: categoryAttributes
    }).then(( categoryData) => {
        done(null, categoryData)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}

exports.post = (body, done) => {
    Category.find({
        where:{
            categoryName: body.categoryName
        }
    }).then((data) => {
        if(data){
            done({error: "This category is already available"})
        }else{
            Category.create(body).
            then((newCategory) => {
                done(null, newCategory)
            }).catch((err) => {
                done({error: err.message, detail:err})
            })
        }
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}