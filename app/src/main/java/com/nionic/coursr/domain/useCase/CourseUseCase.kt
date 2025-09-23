package com.nionic.coursr.domain.useCase

import com.nionic.coursr.domain.model.Course
import com.nionic.coursr.domain.repository.CourseRepository
import kotlinx.coroutines.flow.Flow

class CourseUseCase(private val repository: CourseRepository) {

    operator fun invoke(): Flow<List<Course>> = repository.getAllCourses()
}