package com.nionic.coursr.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nionic.coursr.data.local.entity.CourseEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCourses(courses: List<CourseEntity>)

    @Update
    suspend fun updateCourse(course: CourseEntity)

    @Query("SELECT *FROM course_data_table")
    fun getAllCourses(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM course_data_table WHERE hasLike = 1")
    fun getFavCourses(): Flow<List<CourseEntity>>
}