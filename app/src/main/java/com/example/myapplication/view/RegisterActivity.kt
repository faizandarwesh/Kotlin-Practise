package com.example.myapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.entities.User
import com.example.myapplication.utils.isValidEmail
import com.example.myapplication.utils.showToast
import com.example.myapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private var isValidUser:Boolean = false
    private var userViewModel:UserViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

    }

    private fun init() {

        supportActionBar!!.hide()
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        initWidgets()
    }

    private fun initWidgets() {

        isValidUser = fieldValidation()

        if (isValidUser){
            registerUser()
        }
    }

    private fun fieldValidation():Boolean {

        var valid = true

        sign_up_button.setOnClickListener {
            if (TextUtils.isEmpty(enter_username.text.toString()) && TextUtils.isEmpty(enter_password.text.toString())
                && TextUtils.isEmpty(enter_email.text.toString())
            ) {
                enter_username.error = getString(R.string.empty_username)
                enter_email.error = getString(R.string.empty_email)
                enter_password.error = getString(R.string.empty_password)
                showToast(getString(R.string.empty_fields))
                valid = false
            }

            if (TextUtils.isEmpty(enter_username.text.toString())) {
                enter_username.error = getString(R.string.empty_username)
                valid = false
            }
            if (TextUtils.isEmpty(enter_email.text.toString())) {
                enter_email.error = getString(R.string.empty_email)
                valid = false
            }
            if (!TextUtils.isEmpty(enter_email.text.toString()) && !isValidEmail(enter_email.text.toString())) {
                enter_email.error = getString(R.string.invalid_email)
                valid = false
            }
            if (TextUtils.isEmpty(enter_password.text.toString())) {
                enter_password.error = getString(R.string.empty_password)
                valid = false
            }
            if (!TextUtils.isEmpty(enter_email.text.toString()) && enter_password.text.toString().length < 6) {
                enter_password.error = getString(R.string.password_less_digit_error)
                valid = false
            }
        }

        return valid
    }

    private fun registerUser(){
        userViewModel!!.insert(User(username = enter_username.text.toString(),email = enter_email.text.toString(),password = enter_password.text.toString()))
    }

}
