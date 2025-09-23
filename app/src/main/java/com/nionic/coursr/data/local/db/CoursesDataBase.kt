package com.nionic.coursr.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nionic.coursr.data.local.dao.CourseDao
import com.nionic.coursr.data.local.dao.UserDao
import com.nionic.coursr.data.local.entity.CourseEntity
import com.nionic.coursr.data.local.entity.UserEntity

@Database(
    entities = [CourseEntity::class, UserEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CoursesDataBase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
    abstract fun userDao(): UserDao
}