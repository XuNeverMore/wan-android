package com.nevermore.androidplay.account

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.nevermore.androidplay.R
import com.nevermore.androidplay.RetrofitHelper
import com.nevermore.androidplay.data.ResponseEntity
import com.nevermore.androidplay.data.Result
import com.nevermore.androidplay.data.ResultCallback
import com.nevermore.androidplay.data.UserBean

class AccountViewModel(application: Application) : AndroidViewModel(application) {

    val isLoginIn: MutableLiveData<Boolean> = MutableLiveData(false)


    val registerResult: MutableLiveData<Result<ResponseEntity<UserBean>>> = MutableLiveData()

    val registerState: MutableLiveData<RegisterState> = MutableLiveData()

    val loginState: MutableLiveData<RegisterState> = MutableLiveData()

    val loginResult: MutableLiveData<Result<ResponseEntity<UserBean>>> = MutableLiveData()

    val userInfo: MutableLiveData<UserBean> = MutableLiveData()


    fun clearRegisterResult() {
        registerResult.value = null
    }


    fun changeContent(
        userName: String,
        password: String,
        rePassword: String? = null,
        type: Int = 0
    ) {
        if (type == 0) {
            if (!isUserNameValid(userName)) {
                registerState.value = RegisterState(userNameError = R.string.error_username)
            } else if (!isPasswordValid(password)) {
                registerState.value = RegisterState(passwordError = R.string.error_password)
            } else if (rePassword != password) {
                registerState.value = RegisterState(rePassword = R.string.error_repassword)
            } else {
                registerState.value = RegisterState(isValie = true)
            }
        } else {
            if (!isUserNameValid(userName)) {
                loginState.value = RegisterState(userNameError = R.string.error_username)
            } else if (!isPasswordValid(password)) {
                loginState.value = RegisterState(passwordError = R.string.error_password)
            } else {
                loginState.value = RegisterState(isValie = true)
            }
        }


    }

    private fun isUserNameValid(userName: String): Boolean {
        return userName.isNotEmpty()
    }


    private fun isPasswordValid(password: String): Boolean = password.length > 5

    fun register(
        userName: String,
        password: String,
        rePassword: String
    ) {
        RetrofitHelper.run {
            getService().register(userName, password, rePassword)
                .enqueue(object : ResultCallback<ResponseEntity<UserBean>>() {
                    override fun onResult(result: Result<ResponseEntity<UserBean>>) {
                        registerResult.value = result
                    }

                })
        }

    }

    fun login(userName: String, password: String) {
        RetrofitHelper.getService().login(userName, password)
            .enqueue(object : ResultCallback<ResponseEntity<UserBean>>() {
                override fun onResult(result: Result<ResponseEntity<UserBean>>) {
                    loginResult.value = result
                    if (result is Result.Success) {
                        userInfo.value = result.t.data
                        isLoginIn.value = true
                    }
                }
            })

    }


}

class RegisterState(
    val userNameError: Int? = null,
    val passwordError: Int? = null,
    val rePassword: Int? = null,
    val isValie: Boolean = false
)