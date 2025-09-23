package com.nionic.coursr.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.navigation.NavigationBarView
import com.nionic.coursr.R
import com.nionic.coursr.databinding.ActivityMainBinding
import com.nionic.coursr.loadFragment
import com.nionic.coursr.presentation.ui.screens.AccountFragment
import com.nionic.coursr.presentation.ui.screens.CoursesFragment
import com.nionic.coursr.presentation.ui.screens.FavoritesCoursesFragment
import com.nionic.coursr.presentation.viewModel.CourseViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private var binding: ActivityMainBinding? = null
    private val courseViewModel: CourseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupLoadingCourses()

        loadFragment(this, CoursesFragment(), R.id.contentLayout)

        binding?.bottomNav?.selectedItemId = R.id.bmHome
        binding?.bottomNav?.setOnItemSelectedListener(this)
    }

    private fun setupLoadingCourses() {
        lifecycleScope.launch {
            courseViewModel.allCourses.collect { courses ->
                if (courses.isEmpty()) {
                    courseViewModel.loadCourses()
                }
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bmHome ->
                loadFragment(
                    this,
                    CoursesFragment(),
                    R.id.contentLayout
                )

            R.id.bmFavorites ->
                loadFragment(
                    this,
                    FavoritesCoursesFragment(),
                    R.id.contentLayout
                )

            R.id.bmAccount ->
                loadFragment(
                    this,
                    AccountFragment(),
                    R.id.contentLayout
                )
        }
        return true
    }
}