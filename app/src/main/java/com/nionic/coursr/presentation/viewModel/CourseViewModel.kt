package com.nionic.coursr.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nionic.coursr.domain.model.Course
import com.nionic.coursr.domain.useCase.courses.CourseUseCase
import com.nionic.coursr.domain.useCase.courses.FavUseCase
import com.nionic.coursr.domain.useCase.courses.LoadCoursesUseCase
import com.nionic.coursr.domain.useCase.courses.ToggleFavUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CourseViewModel : ViewModel(), KoinComponent {

    private val coursesUseCase: CourseUseCase by inject()
    private val favCoursesUseCase: FavUseCase by inject()
    private val loadCoursesUseCase: LoadCoursesUseCase by inject()
    private val toggleFavUseCase: ToggleFavUseCase by inject()

    private val _isSortedByDate = MutableStateFlow(false)
    val isSortedByDate: StateFlow<Boolean> = _isSortedByDate.asStateFlow()

    val allCourses: StateFlow<List<CourseUiState>> = coursesUseCase().map { courses ->
        courses.map(CourseUiState::fromDomain)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )
    val favCourses: StateFlow<List<CourseUiState>> = favCoursesUseCase().map { courses ->
        courses.map(CourseUiState::fromDomain)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val sortedCourses: StateFlow<List<CourseUiState>> =
        combine(allCourses, isSortedByDate) { courses, isSorted ->
            if (isSorted) courses.sortedByDescending { it.publishDate }
            else courses
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    fun loadCourses() {
        viewModelScope.launch { loadCoursesUseCase() }
    }

    fun toggleFavCourses(courseId: Int) {
        viewModelScope.launch { toggleFavUseCase(courseId) }
    }

    fun sortByPublishDate() {
        _isSortedByDate.value = !_isSortedByDate.value
    }
}

data class CourseUiState(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
) {
    companion object {
        fun fromDomain(course: Course) = CourseUiState(
            id = course.id,
            title = course.title,
            text = course.text,
            price = course.price,
            rate = course.rate,
            startDate = course.startDate,
            hasLike = course.hasLike,
            publishDate = course.publishDate
        )
    }
}