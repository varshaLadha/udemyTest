const {Router} = require('express');
const router = Router();
const {post} = require('./../controllers/chapterDetail.controller');

router.post('/',(req, res) => {
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

module.exports = router;