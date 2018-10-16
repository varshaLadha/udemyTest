const Category = require('./../schema/category.schema')
const {categoryAttributes} = require('./../configs/general')
const subCategory = require('./../schema/subcategory.schema')
const Course = require('./../schema/course.schema')

Category.hasMany(subCategory, {foreignKey: 'categoryId'})
subCategory.belongsTo(Category, {foreignKey: 'categoryId'})

subCategory.hasMany(Course, {foreignKey: 'subCategoryId'})
Course.belongsTo(subCategory, {foreignKey: 'subCategoryId'})

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

/*
exports.getDetail = (id, done) => {
    Category.find({
        where: {
            id: id,
            isDeleted: false
        }
    }).then((data) => {
        if(data) {
            subCategory.findAll({
                where: {
                    categoryId: id,
                    isDeleted: false
                }
            }).then((detailData) => {
                if(detailData.length > 0){
                    done(null, detailData)
                }else {
                    done({error: "No detail data available"})
                }
            }).catch((err) => {
                done({error: err.message, detail:err})
            })
        }else {
            done({error: "No data available"})
        }
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}*/

exports.getDetail = (id, done) => {
    Category.find({
        where: {
            id: id,
            isDeleted: false
        },
        include: [subCategory]
    }).then((data) => {
        if (data) {
            done(null, data)
        } else {
            done({error: "No data available"})
        }
    }).catch((err) => {
        done({error: err.message, detail: err})
    })
}

exports.getPopularCourse = (id, done) => {
    Category.findAll({
        where: {
            id: id,
            isDeleted: false
        },
        include: [subCategory]
    }).then((data) => {
        if (data) {
            done(null, data)
        } else {
            done({error: "No data available"})
        }
    }).catch((err) => {
        done({error: err.message, detail: err})
    })
}