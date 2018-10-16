const {Router} = require('express');
const router = Router();

const {getByCourseId, post} = require('./../controllers/courseDetail.controller')

router.get('/course/:courseId', (req, res) => {
    getByCourseId(req.params.courseId, (err, result) => {
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

module.exports = router