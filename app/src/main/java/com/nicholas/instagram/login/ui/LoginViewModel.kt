package com.nicholas.instagram.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>() //Creamos una instancia privada que solo se pueda modificar desde dentro
    val password: LiveData<String> = _password // El modificador de la instancia.

    private val _isLogin =  MutableLiveData<Boolean>()
    val isLogin:LiveData<Boolean> = _isLogin

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
        _isLogin.value = enableLogin(email, password)
    }

    fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6
}