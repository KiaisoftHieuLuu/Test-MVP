package com.example.mvpexam.login.model

class User(var username: String?, var password: String?) {

    fun isValidUsername(): Boolean {
        return username?.isNotEmpty()== true
    }

    fun isValidPassword(): Boolean {
        return password != null && password!!.length  >= 6
    }
}