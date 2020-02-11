package com.example.myapplication.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.myapplication.database.MyDatabase
import com.example.myapplication.database.UserDao
import com.example.myapplication.entities.User

class UserRepository(application: Application) {

    private var userDao:UserDao? = null
    private var userData:LiveData<List<User>>? = null

    init {
       val database = MyDatabase.getInstance(application)
        userDao = database.userDao
        userData = userDao!!.getUsers()
    }

    fun insert(user: User){
      InsertData(userDao).execute(user)
    }

    fun update(user: User){
        UpdateData(userDao).execute(user)
    }

    fun delete(user: User){
        DeleteData(userDao).execute(user)
    }

    fun deleteAll(){
        DeleteAllData(userDao).execute()
    }

    fun getAllUsers(): LiveData<List<User>>? {
        return userData
    }

    fun checkUserExist(user:User) {
        queryForUser(userDao).execute(user)
    }

    private class InsertData(val userDao: UserDao?): AsyncTask<User, Void, Void>()
    {
        override fun doInBackground(vararg user: User?): Void? {
            userDao!!.insert(user[0]!!)
            return null
    }
    }

    private class DeleteData(val userDao: UserDao?) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg user: User?): Void? {
            userDao!!.delete(user[0]!!)
            return null
        }

    }

    private class UpdateData(val userDao: UserDao?):AsyncTask<User,Void,Void>(){

        override fun doInBackground(vararg user: User?): Void? {
            userDao!!.update(user[0]!!)
            return null
        }

    }

    private class DeleteAllData(val userDao: UserDao?) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg user: User?): Void? {
            userDao!!.deleteAll()
            return null
        }

    }

    private class queryForUser(val userDao: UserDao?) : AsyncTask<User, Void, Void>() {

        override fun doInBackground(vararg user: User?): Void? {
            userDao!!.checkUserRegistration()
            return null
        }

    }

}

