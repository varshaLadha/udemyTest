const Sequelize = require('sequelize');
const {db} = require('./../configs/database.js');
var bcrypt = require('bcrypt');

const userSchema = db.define('User', {
    id:{
        type: Sequelize.BIGINT,
        autoIncrement: true,
        primaryKey: true
    },
    userName: {
        type: Sequelize.STRING,
        allowNull : false,
        validate: {
            notEmpty: { msg: 'user name is required field'}
        }
    },
    email: {
        type: Sequelize.STRING,
        unique:true,
        validate: {
            notEmpty: { msg: 'Email is required field.'},
            isEmail: { msg: 'Please Enter valid email.'}
        }
    },
    password:{
        type: Sequelize.STRING
    },
    loginType:{
        type: Sequelize.STRING,
        allowNull: false,
        validate: {
            notEmpty: { msg: 'Please porvide login type'}
        }
    },
    emailTerms:{
      type:Sequelize.BOOLEAN,
        defaultValue: false,
    },
    isDeleted:{
        type:Sequelize.BOOLEAN,
        defaultValue: false,
    }
});

userSchema.sync({force:false}).then((res) => {
    console.log('User Table Created Successfully');
}).catch((error) => {
    console.log(error);
})

module.exports = userSchema;