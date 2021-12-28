package com.example.mvpexam.login

import com.example.mvpexam.login.model.ErrorResponse
import com.example.mvpexam.login.model.Token
import com.example.mvpexam.login.model.User
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response

class LoginInteractor {

    interface onLoginListener{
        fun onSuccess(token: String)
        fun onError(error: String)
        fun onFailure()
    }

    fun login(user: User, listener: onLoginListener){
        ApiService.api.sendUser(user).enqueue(object : retrofit2.Callback<Token?>{
            override fun onResponse(call: Call<Token?>, response: Response<Token?>) {
                val gson = Gson()
                val errorResponse: String? = response.errorBody()?.string()
                val token: Token? = response.body()

                if (errorResponse?.isNotEmpty() == true){
                    val error: ErrorResponse = gson.fromJson(errorResponse, ErrorResponse::class.java)
                    listener.onError(error.msg)
                    return
                }
                if (token?.token?.isNotEmpty() == true){
                    token.token
                    listener.onSuccess(token.token)
                    return
                }
            }

            override fun onFailure(call: Call<Token?>, t: Throwable) {
                listener.onFailure()
            }

        })
    }

}