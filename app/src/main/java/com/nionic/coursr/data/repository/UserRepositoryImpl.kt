package com.nionic.coursr.data.repository

import com.nionic.coursr.data.local.dao.UserDao
import com.nionic.coursr.data.local.entity.UserEntity
import com.nionic.coursr.data.mapper.toDomain
import com.nionic.coursr.data.mapper.toEntity
import com.nionic.coursr.domain.model.User
import com.nionic.coursr.domain.repository.UserRepository
import com.nionic.coursr.mapList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val dao: UserDao) : UserRepository {

    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    override suspend fun insertUser(name: String, email: String) {
        withContext(ioDispatcher) {
            val userEntity = UserEntity(id = 0, name = name, email = email)
            
        }
    }

    override fun getUser(): Flow<List<UserEntity>> {
        return dao.getUser()
    }

    override suspend fun deleteUser() {
        dao.deleteUser()
    }
}