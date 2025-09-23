package com.nionic.coursr.presentation.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.nionic.coursr.databinding.FragmentFavoritesCoursesBinding
import com.nionic.coursr.presentation.adapters.CoursesAdapter
import com.nionic.coursr.presentation.viewModel.CourseUiState
import com.nionic.coursr.presentation.viewModel.CourseViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesCoursesFragment : Fragment() {

    private var binding: FragmentFavoritesCoursesBinding? = null
    private var adapter: CoursesAdapter? = null
    private val courseViewModel: CourseViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesCoursesBinding.inflate(layoutInflater, container, false)

        initRecycler()
        setupObservers()

        return binding?.root
    }

    private fun initRecycler() {
        binding?.rvFavCourses?.layoutManager = LinearLayoutManager(context)
        adapter = CoursesAdapter { course: CourseUiState -> putCourseInFavorites(course) }
        binding?.rvFavCourses?.adapter = adapter
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            courseViewModel.favCourses.collect { courses ->
                adapter?.updateData(courses)
            }
        }
    }

    private fun putCourseInFavorites(course: CourseUiState) {
        courseViewModel.toggleFavCourses(course.id)
    }
}