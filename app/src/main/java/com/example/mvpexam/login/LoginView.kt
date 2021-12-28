package com.example.mvpexam.login

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setUsernameInvalid()
    fun setPasswordInvalid()
    fun setConnectionFailure()
    fun setError(error: String)
    fun setSuccess(token: String)
}