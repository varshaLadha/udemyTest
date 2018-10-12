const {Router} = require('express');
const router = Router();

const {getByCategoryId, post} = require('./../controllers/subcategory.controller')

router.get('/category/:categoryId', (req, res) => {
    getByCategoryId(req.body.categoryId, (err, result) => {
        if(err) {
            res.json({success:0, response:err, token:''})
        }else if(result.length == 0){
            res.json({success:0, response:{"error":"No data found"}})
        }else {
            res.json({success:1, response: result})
        }
    })
})

router.post('/', (req, res) => {
    post(req.body, (err, result) => {
        if(err) {
            res.json({success:0, response:err})
        }else {

            delete result.dataValues.isDeleted
            delete result.dataValues.createdAt
            delete result.dataValues.updatedAt

            res.json({success:1, response: result.dataValues})
        }
    })
})

module.exports = router