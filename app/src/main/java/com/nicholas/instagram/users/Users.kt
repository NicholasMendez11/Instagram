package com.example.progressbartest.users

import android.provider.ContactsContract
import androidx.annotation.DrawableRes
import com.nicholas.instagram.R


data class User(@DrawableRes val profilepicId: Int, val email: String,)


val AllUsers = listOf<User>(
    User(R.drawable.pic1,"nicholasmendez@gmail.com"),
    User(R.drawable.pic3,"nicholas@gmail.com"),

)