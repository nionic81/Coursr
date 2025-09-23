package com.nionic.coursr.domain.repository

import com.nionic.coursr.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun insertUser(name: String, email: String)

    fun getUser(): Flow<List<UserEntity>>

    suspend fun deleteUser()
}