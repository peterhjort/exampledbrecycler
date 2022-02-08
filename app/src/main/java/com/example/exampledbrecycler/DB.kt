package com.example.exampledbrecycler

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Player::class], version = 1, exportSchema = false)
abstract class PlayerDB: RoomDatabase() {
    abstract val playerDAO: PlayerDao
    companion object {
        @Volatile
        private var INSTANCE: PlayerDB? = null
        // context comes from MyApp, may also be provided as parameter
        fun getInstance(context: Context = MyApp.appContext): PlayerDB {
            synchronized(this) {
                var instance =
                    INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        PlayerDB::class.java,
                        "player_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}