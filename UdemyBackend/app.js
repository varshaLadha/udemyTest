const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')

const userRoutes = require('./routes/user.route')
const categoryRoutes = require('./routes/category.route')
const subCategoryRoutes = require('./routes/subcategory.route')
const courseRoutes = require('./routes/course.route')

const {db} = require('./configs/database')

let app = express();
app.use(cors());

db.authenticate()
    .then(() => {
        console.log('Connection to database has been established successfully.');
    })
    .catch(err => {
        console.error('Unable to connect to the database:', err);
    });

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

app.use('/user', userRoutes);
app.use('/category', categoryRoutes)
app.use('/subcategory', subCategoryRoutes)
app.use('/course', courseRoutes)

app.use((err, req, res, next) => {
    res.status(err.status || 500);
    res.json({
        message: err.message,
        detail: err
    });
});
app.listen(3000, (err, res) => {
    if(err){
        console.log("Error occurred "+err.toString());
    } else {
        console.log("Server is listening on port 3000")
    }
})