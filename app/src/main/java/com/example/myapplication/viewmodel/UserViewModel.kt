package com.example.myapplication.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.entities.User
import com.example.myapplication.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var repository:UserRepository? = null
    private var allUsers:LiveData<List<User>>? = null

    init {
        repository = UserRepository(application)
        allUsers = repository!!.getAllUsers()
    }

    fun insert(user: User){
        repository!!.insert(user)
    }

    fun update(user: User){
        repository!!.update(user)
    }

    fun delete(user: User){
        repository!!.delete(user)
    }

    fun deleteAll(){
        repository!!.deleteAll()
    }

    fun getAllUsers():LiveData<List<User>>?{
        return allUsers
    }

    fun repoCheckUser(username:String,password:String){
        repository!!.checkUserExist(username,password)
    }
}