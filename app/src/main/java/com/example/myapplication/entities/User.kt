package com.example.myapplication.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(@PrimaryKey(autoGenerate = true) var id:Int=0, val username:String,val email:String, val password:String)
