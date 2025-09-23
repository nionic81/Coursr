package com.nionic.coursr.domain.useCase

import com.nionic.coursr.domain.repository.CourseRepository

class ToggleFavUseCase(private val repository: CourseRepository) {

    suspend operator fun invoke(courseId: Int) = repository.toggleFav(courseId)
}