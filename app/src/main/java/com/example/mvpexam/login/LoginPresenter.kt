package com.example.mvpexam.login

import com.example.mvpexam.login.model.User

class LoginPresenter(var loginView: LoginView?, val loginInteractor: LoginInteractor) :
    LoginInteractor.onLoginListener {
    fun validateLogin(user: User) {
        if (!user.isValidUsername()) {
            loginView?.apply {
                setUsernameInvalid()
            }
            return
        }
        if (!user.isValidPassword()) {
            loginView?.apply {
                setPasswordInvalid()
            }
            return
        }

        //login request
        loginView?.apply {
            showProgress()
        }
        loginInteractor.login(user, this)
    }


    override fun onSuccess(token: String) {
        loginView?.apply {
            setSuccess(token)
            hideProgress()
        }
    }

    override fun onError(error: String) {
        loginView?.apply {
            setError(error)
            hideProgress()
        }
    }

    override fun onFailure() {
        loginView?.apply {
            setConnectionFailure()
            hideProgress()
        }
    }
}