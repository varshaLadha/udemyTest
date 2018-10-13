//business https://bannbiz.com/wp-content/uploads/2011/05/people-doing-business.jpg
//design https://www.incomediary.com/wp-content/uploads/2015/01/UI-for-Mobile-App-1024x662.jpg
//photography http://caitlintphotography.com/wp-content/uploads/2017/01/photography.jpg
//lifestyle https://images.unsplash.com/photo-1529333166437-7750a6dd5a70?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=76ba3dc274cc7026595de36ee1d63da4&w=1000&q=80
//health and fitness http://www.ymcachicago.org/page/-/images/programs/banner_healthfitness.jpg
//teacher training https://gooduniversities.com.au/wp-content/uploads/school-teacher-classroom.jpg
//music https://media.istockphoto.com/photos/ancient-piano-keyboard-picture-id495294754?k=6&m=495294754&s=612x612&w=0&h=T-SAJfRkF2L9QhgO9PBq1FQXlyy2LYxJ9CeFIIUU1XE=
//language https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpgC-fQCo9FF3zNyo_Hd9BNslGobMpUI-2mjGHsuobgdeYzuKTPg
//development http://www.innovativeconsulting.co.in/wp-content/uploads/2016/01/CMS-e1455189443420.png
//marketing http://www.d.umn.edu/~jvileta/images-marketingbooks/marketingbooks5.jpg

const {Router} = require('express');
const router = Router();
const {getAll, post} = require('./../controllers/category.controller');

router.get('/', (req, res) => {
    getAll((err, result) => {
        if(err){
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

module.exports = router;