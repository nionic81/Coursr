package com.nionic.coursr.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nionic.coursr.data.local.dao.CourseDao
import com.nionic.coursr.data.local.entity.CourseEntity

@Database(
    entities = [CourseEntity::class],
    exportSchema = false,
    version = 1
)
abstract class CourseDB : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}