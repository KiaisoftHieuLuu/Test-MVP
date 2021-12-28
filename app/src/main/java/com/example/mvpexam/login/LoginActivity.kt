package com.example.mvpexam.login

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mvpexam.R
import com.example.mvpexam.login.model.User

class LoginActivity : AppCompatActivity(), LoginView {

    private lateinit var loginButton: Button
    private lateinit var userName: EditText
    private lateinit var password: EditText
    private lateinit var progressBar: ProgressBar

    private val presenter = LoginPresenter(this, LoginInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.bt_login)
        userName = findViewById(R.id.et_username)
        password = findViewById(R.id.et_password)
        progressBar = findViewById(R.id.progress_bar)

        loginButton.setOnClickListener { validateLogin() }
    }

    private fun validateLogin() {
        val user = User(userName.text.toString(), password.text.toString())
        presenter.validateLogin(user)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setUsernameInvalid() {
        userName.error = "invalid user name"
    }

    override fun setPasswordInvalid() {
        password.error = "invalid password"
    }

    override fun setConnectionFailure() {
        Toast.makeText(this, "Connection Error", Toast.LENGTH_SHORT).show()
    }

    override fun setError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }

    override fun setSuccess(token: String) {
        Toast.makeText(this, "token: " + token, Toast.LENGTH_SHORT).show()
    }

}