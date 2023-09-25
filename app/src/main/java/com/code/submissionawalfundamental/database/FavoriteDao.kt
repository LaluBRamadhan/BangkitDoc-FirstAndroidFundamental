package com.code.submissionawalfundamental.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
@Entity
interface FavoriteDao {
    @Query("SELECT * FROM FavoriteUser ORDER BY id ASC")
    fun getAllFavorite(): LiveData<List<FavoriteUser>>

    @Query("SELECT * FROM FavoriteUser WHERE name = :username LIMIT 1")
    fun getUserByUsername(username: String): FavoriteUser?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: FavoriteUser)

    @Delete
    fun deleteUser(user: FavoriteUser)

}