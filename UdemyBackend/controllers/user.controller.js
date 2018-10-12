const User = require('./../schema/user.schema')

var bcrypt = require('bcrypt');

//method for encrypting password while updating password
var generateHash = (password) => {
    return bcrypt.hash(password, 10);
}

//method for validating password while logging in
var validPassword = (password,hash) => {
    return bcrypt.compare(password, hash);
}

exports.login = (body, done) => {
    User.find({
        where:{
            email: body.email,
            isDeleted: false
        }
    }).then(async (user) => {
        if(user){
            if(body.loginType == "google"){
                done(null, user)
            }
            else if(body.loginType == user.loginType && await validPassword(body.password, user.password)){
                done(null, user)
            }else {
                done({error: "Please check you email and password"})
            }
        }else {
            if(body.loginType == "google"){
                User.create(body).
                then((newUser) => {
                    done(null,newUser)
                }).
                catch((err) => {
                    done({error: err.message, detail:err});
                })
            }else {
                done({error: "Please check you email and password"})
            }
        }
    }).catch((err) => {
        done({error: err.message, detail:err})
    })
}

exports.post = (body, done) => {
    User.find({
        where:{
            email: body.email
        }
    }).then(async (user) => {
        if(user){
            done({error: "This email is already in use"})
        }else {
            if(body.password){
                body.password = await generateHash(body.password)
            }
            User.create(body).
            then((newUser) => {
                done(null,newUser)
            }).
            catch((err) => {
                done({error: err.message, detail:err});
            })
        }
    }).catch((err) => {
       done({error: err.message, detail:err});
    })
}