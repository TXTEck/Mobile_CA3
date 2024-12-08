package com.example.homeguard.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = [User::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "homeguard_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(prepopulateCallback) // Add callback here
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private val prepopulateCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                println("onCreate called: Starting prepopulation...")
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        INSTANCE?.let { database ->
                            val user = User(
                                name = "John Smith",
                                address = "19 Rockfield Court, Marshes Upper"
                            )
                            database.userDao().insertUser(user)
                            println("Prepopulated user inserted: $user")
                        }
                    } catch (e: Exception) {
                        println("Error during prepopulation: ${e.message}")
                    }
                }
            }
        }

    }
}
