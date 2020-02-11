package com.example.myapplication.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.entities.User

@Dao
interface UserDao {

   @Insert
   fun insert(user : User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM users")
    fun deleteAll()

    @Query("SELECT * FROM users ORDER BY username DESC")
    fun getUsers(): LiveData<List<User>>

    @Query("SELECT COUNT(*) FROM users WHERE username= :username AND password= :password")
    fun checkUserRegistration(username:String,password:String) : Int

    @Query("SELECT * FROM users WHERE email =:email")
    fun checkDuplicateEmail(email:String) : Boolean
}