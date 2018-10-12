const SubCategory = require('./../schema/subcategory.schema')
const {subCategoryAttributes} = require('./../configs/general')

exports.getByCategoryId = (categoryId, done) => {
    SubCategory.findAll({
        where: {
            categoryId: categoryId,
            isDeleted: false
        },
        raw: true,
        attributes: subCategoryAttributes
    }).then((data) => {
        done(null, data)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}

exports.post = (body, done) => {
    SubCategory.create(body).
    then((newSubCategory) => {
        done(null, newSubCategory)
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}