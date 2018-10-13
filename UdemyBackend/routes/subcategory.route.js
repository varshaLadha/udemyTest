const {Router} = require('express');
const router = Router();

const {getByCategoryId, post} = require('./../controllers/subcategory.controller')

router.get('/category/:categoryId', (req, res) => {
    getByCategoryId(req.params.categoryId, (err, result) => {
        if(err) {
            res.json({success:0, error:err.error, token:''})
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