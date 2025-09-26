package com.nionic.coursr.data.local.db

import android.content.Context
import androidx.room.Room

object DbProvider {

    private var INSTANCE: CourseDB? = null

    fun getDB(context: Context): CourseDB {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CourseDB::class.java,
                "courses_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}