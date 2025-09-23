package com.nionic.coursr.presentation.adapters

import android.annotation.SuppressLint
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.nionic.coursr.R
import com.nionic.coursr.databinding.CourseItemBinding
import com.nionic.coursr.presentation.viewModel.CourseUiState
import com.nionic.coursr.stringToLocalDate

class CoursesAdapter(
    private val putCourseInFavorites: (CourseUiState) -> Unit
) : RecyclerView.Adapter<CoursesAdapter.CoursesHolder>() {

    private val courseList = ArrayList<CourseUiState>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CoursesHolder {
        val binding: CourseItemBinding =
            CourseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursesHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(
        holder: CoursesHolder,
        position: Int
    ) {
        holder.bind(courseList[position], putCourseInFavorites)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    class CoursesHolder(private val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("DiscouragedApi")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(
            courses: CourseUiState,
            putCourseInFavorites: (CourseUiState) -> Unit
        ) {
            val dateInLocalDate = stringToLocalDate(courses.publishDate)
            val imageName = "image_${courses.id}"
            val imageResId = itemView.context.resources.getIdentifier(
                imageName,
                "drawable",
                itemView.context.packageName
            )
            binding.tvTitleCourse.text = courses.title
            binding.tvTextCourse.text = courses.text
            binding.tvTextRate.text = courses.rate
            binding.tvPriceCourse.text =
                itemView.context.getString(R.string.price_rubles, courses.price)
            binding.tvPublishDate.text = dateInLocalDate
            binding.imCourse.setImageResource(imageResId)
            if (courses.hasLike) {
                binding.imBtFavorite.visibility = View.INVISIBLE
                binding.imBtFavoriteIn.visibility = View.VISIBLE
            } else {
                binding.imBtFavorite.visibility = View.VISIBLE
                binding.imBtFavoriteIn.visibility = View.INVISIBLE
            }
            binding.imBtFavorite.setOnClickListener { putCourseInFavorites(courses) }
            binding.imBtFavoriteIn.setOnClickListener { putCourseInFavorites(courses) }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<CourseUiState>) {
        courseList.clear()
        courseList.addAll(list)
        notifyDataSetChanged()
    }
}