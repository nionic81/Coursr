package com.nionic.coursr.data.local.db

import android.content.Context
import androidx.room.Room

object DbProvider {

    private var INSTANCE: CoursesDataBase? = null

    fun getDB(context: Context): CoursesDataBase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                CoursesDataBase::class.java,
                "courses_db"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}