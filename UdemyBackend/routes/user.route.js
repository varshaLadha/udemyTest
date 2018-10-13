const {Router} = require('express');
const router = Router();
const jwt = require('jsonwebtoken');
const {jwtConfig} = require('./../configs/general')

const {login, post} = require('./../controllers/user.controller')

router.post('/login', (req, res) =>{
    console.log(req.body)
    login(req.body, (err, user) => {
        if(err){
            //res.statusCode = 400
            res.json({success:0, error:err.error, token:''})
        }else {
            const JWTToken = jwt.sign({
                email: user.email,
                _id: user.id,
            },
                jwtConfig.secret
            );

            delete user.dataValues.id
            delete user.dataValues.createdAt
            delete user.dataValues.updatedAt
            delete user.dataValues.password
            delete user.dataValues.isDeleted
            delete user.dataValues.emailTerms

            //res.statusCode = 200
            res.json({success:1, response: user.dataValues, token:JWTToken})
        }
    })
})

router.post('/signUp', (req, res) => {
    post(req.body, (err, user) => {
        if(err){
            //res.statusCode = 400
            res.json({success:0, error:err.error, token:''})
        }
        else {
            const JWTToken = jwt.sign({
                    email: user.email,
                    _id: user.id,
                },
                jwtConfig.secret
            );

            delete user.dataValues.id
            delete user.dataValues.createdAt
            delete user.dataValues.updatedAt
            delete user.dataValues.password
            delete user.dataValues.isDeleted
            delete user.dataValues.emailTerms

            //res.statusCode = 200
            res.json({success:1, response: user.dataValues, token:JWTToken})
        }
    })
})

module.exports = router;