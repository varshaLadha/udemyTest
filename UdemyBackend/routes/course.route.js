const {Router} = require('express');
const router = Router();

const {getBySubCategoryId, post, popularCoursesByCategoryId, popularCourses,searchCourse,getAllCourse} = require('./../controllers/course.controller')

router.get('/subcategory/:subCategoryId', (req, res) => {
    getBySubCategoryId(req.params.subCategoryId, (err, result) => {
        if(err) {
            res.json({success:0, error:err.error})
        }else if(result.length == 0){
            res.json({success:0, error:"No data found"})
        }else {
            res.json({success:1, response: result})
        }
    })
})

router.post('/', (req, res) => {
    post(req.body, (err, result) => {
        if(err) {
            res.json({success:0, error:err.error})
        }else {

            delete result.dataValues.isDeleted
            delete result.dataValues.createdAt
            delete result.dataValues.updatedAt

            res.json({success:1, response: result.dataValues})
        }
    })
})

router.get('/popularCourse/:categoryId', (req, res) => {
    popularCoursesByCategoryId(req.params.categoryId, (err, result) => {
        if(err) {
            res.json({success:0, error:err.error})
        }else if(result.length == 0){
            res.json({success:0, error:"No data found"})
        }else {
            res.json({success:1, response: result})
        }
    })
})

router.get('/popularCourse', (req, res) => {
    popularCourses((err, result) => {
        if(err) {
            res.json({success:0, error:err.error})
        }else if(result.length == 0){
            res.json({success:0, error:"No data found"})
        }else {
            res.json({success:1, response: result})
        }
    })
})

router.get('/searchCourse/:searchKey', (req, res) => {
    searchCourse(req.params.searchKey, (err, result) => {
        if(err){
            res.json({success:0, error:err.error})
        }else if(result.length == 0){
            res.json({success:0, error:"No data found"})
        }else {
            res.json({success:1, response: result})
        }
    })
})

router.get('/:id', (req, res) => {
    getAllCourse(req.params.id, (err, result) => {
        if(err){
            res.json({success:0, error:err.error})
        }else if(result.length == 0){
            res.json({success:0, error:"No data found"})
        }else {
            res.json({success:1, response: result})
        }
    })
})

module.exports = router