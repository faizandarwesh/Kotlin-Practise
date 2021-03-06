package com.example.myapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.repository.UserRepository
import com.example.myapplication.utils.showToast
import com.example.myapplication.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_loginctivity.*
import kotlinx.android.synthetic.main.activity_register.*

class LoginActivity : AppCompatActivity(){

    private var isValidUser:Boolean = false
    private var isUserExist:Boolean = false
    private var userViewModel: UserViewModel? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginctivity)

        init()
    }

    private fun init(){

        supportActionBar!!.hide()
        userViewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        initWidgets()
        
    }

    private fun initWidgets() {

        sign_up_option.setOnClickListener {
            startActivity(Intent(applicationContext,RegisterActivity::class.java))
        }

        isValidUser = fieldValidation()

        if (isValidUser) {
            isUserExist = checkUserExist()
        }

        if (isUserExist) {
            sign_in_button.setOnClickListener {
                userLogin()
            }
        } else {
            showToast("Invalid user, Please try again")
        }

    }

    private fun userLogin(){
     startActivity(Intent(applicationContext,RegisterActivity::class.java))
            showToast("Login Successfully !")
    }      
                      
    private fun checkUserExist(): Boolean {

        userViewModel!!.repoCheckUser(sign_in_username.text.toString(),sign_in_password.text.toString())
       return false
    }
                  
    private fun fieldValidation() :Boolean {

        var valid = true
        
        sign_in_button.setOnClickListener {

            if (TextUtils.isEmpty(sign_in_username.text.toString()) && TextUtils.isEmpty(sign_in_password.text.toString()) ) {
                sign_in_username.error = getString(R.string.empty_username)
                sign_in_password.error = getString(R.string.empty_password)
                showToast(getString(R.string.empty_fields))
                valid = false
            }

            if (TextUtils.isEmpty(sign_in_username.text.toString())){
                 sign_in_username.error = getString(R.string.empty_username)
                 valid = false
            }
            if (TextUtils.isEmpty(sign_in_password.text.toString())){
                 sign_in_password.error = getString(R.string.empty_password)
                 valid = false
            }


        }

    return valid

    }
}
