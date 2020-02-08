package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.myapplication.R
import com.example.myapplication.utils.showToast
import kotlinx.android.synthetic.main.activity_loginctivity.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginctivity)

        init()
    }

    private fun init(){

        supportActionBar!!.hide()
        initWidgets()
        fieldValidation()
    }

    private fun initWidgets(){
        sign_up_option.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }
    }

    private fun fieldValidation(){

        sign_in_button.setOnClickListener {

            if (TextUtils.isEmpty(sign_in_username.text.toString()) && TextUtils.isEmpty(sign_in_password.text.toString()) ) {
                sign_in_username.error = getString(R.string.empty_username)
                sign_in_password.error = getString(R.string.empty_password)
                showToast(getString(R.string.empty_fields))
            }

            if (TextUtils.isEmpty(sign_in_username.text.toString()))
                sign_in_username.error = getString(R.string.empty_username)

            if (TextUtils.isEmpty(sign_in_password.text.toString()))
            sign_in_password.error = getString(R.string.empty_password)

            //Sign Up
            if (sign_in_password.text.toString().length < 6)
                sign_in_password.error = getString(R.string.password_less_digit_error)

        }



    }
}
