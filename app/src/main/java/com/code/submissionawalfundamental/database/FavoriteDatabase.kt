package com.code.submissionawalfundamental.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [FavoriteUser::class], version = 1)
abstract class FavoriteDatabase: RoomDatabase() {
    abstract fun favoriteDao():FavoriteDao

    companion object{
        @Volatile
        private var instance: FavoriteDatabase? = null
        @JvmStatic
        fun getDatabase(ctx: Context): FavoriteDatabase{
            if(instance == null){
                synchronized(FavoriteDatabase::class.java){
                    instance = Room.databaseBuilder(ctx.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_database").build()
                }
            }
            return instance as FavoriteDatabase
        }
    }
}