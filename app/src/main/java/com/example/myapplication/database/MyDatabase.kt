package com.example.myapplication.database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.entities.User

@Database(entities = [User::class],exportSchema = false,version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract val userDao:UserDao

    companion object{

        @Volatile
        private var INSTANCE:MyDatabase? = null

        fun getInstance(context: Context):MyDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {

                    instance = Room.databaseBuilder(context,MyDatabase::class.java,"user_database")
                        .fallbackToDestructiveMigration()
                        .addCallback(object : RoomDatabase.Callback(){
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                AddInitialData(instance).execute()
                            }
                        })
                        .build()

                    INSTANCE = instance

                    }
                     return instance
                }

            }
        }

    class AddInitialData(private val db:MyDatabase?) : AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void?): Void? {

            db!!.userDao.insert(User(username = "Test1",email = "test1gmail.com",password = "test1password"))
            db.userDao.insert(User(username = "Test2",email = "test2gmail.com",password = "test2password"))
            db.userDao.insert(User(username = "Test3",email = "test3gmail.com",password = "test3password"))

            return null
        }

    }

    }


