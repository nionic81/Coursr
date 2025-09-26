package com.nionic.coursr.domain.useCase.courses

import com.nionic.coursr.domain.repository.CourseRepository

class LoadCoursesUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(): Result<Unit> = repository.fetchAndSaveCourses()
}