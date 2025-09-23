package com.nionic.coursr.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nionic.coursr.data.local.entity.UserEntity
import com.nionic.coursr.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_data_table")
    fun getUser(): Flow<List<UserEntity>>

    @Delete
    suspend fun deleteUser()
}