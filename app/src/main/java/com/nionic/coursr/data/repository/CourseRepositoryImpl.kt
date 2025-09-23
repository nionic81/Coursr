package com.nionic.coursr.data.repository

import com.nionic.coursr.data.local.dao.CourseDao
import com.nionic.coursr.data.mapper.toDomain
import com.nionic.coursr.data.mapper.toEntity
import com.nionic.coursr.data.remote.RetrofitClient
import com.nionic.coursr.domain.model.Course
import com.nionic.coursr.domain.repository.CourseRepository
import com.nionic.coursr.mapList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class CourseRepositoryImpl(private val dao: CourseDao) : CourseRepository {

    override suspend fun fetchAndSaveCourses(): Result<Unit> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = RetrofitClient.apiService
                .getCourses(RetrofitClient.getDriverUrl())
                .execute()
            if (response.isSuccessful && response.body() != null) {
                val coursesDto = response.body()!!.courses
                val coursesEntity = coursesDto.map { it.toEntity() }
                dao.insertAllCourses(coursesEntity)
                Result.success(Unit)
            } else Result.failure(Exception("${response.code()}"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getAllCourses(): Flow<List<Course>> {
        return dao.getAllCourses()
            .mapList { it.toDomain() }
            .flowOn(Dispatchers.IO)
    }

    override fun getFavCourses(): Flow<List<Course>> {
        return dao.getFavCourses()
            .mapList { it.toDomain() }
            .flowOn(Dispatchers.IO)
    }

    override suspend fun toggleFav(courseId: Int) {
        val course = dao.getAllCourses().first().find { it.id == courseId } ?: return
        val updatedCourse = course.copy(hasLike = !course.hasLike)
        dao.updateCourse(updatedCourse)
    }
}