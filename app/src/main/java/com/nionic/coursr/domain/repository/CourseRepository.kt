package com.nionic.coursr.domain.repository

import com.nionic.coursr.domain.model.Course
import kotlinx.coroutines.flow.Flow

interface CourseRepository {

    suspend fun fetchAndSaveCourses(): Result<Unit>

    fun getAllCourses(): Flow<List<Course>>

    fun getFavCourses(): Flow<List<Course>>

    suspend fun toggleFav(courseId: Int)
}