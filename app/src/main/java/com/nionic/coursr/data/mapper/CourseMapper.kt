package com.nionic.coursr.data.mapper

import com.nionic.coursr.data.local.entity.CourseEntity
import com.nionic.coursr.data.remote.CourseDto
import com.nionic.coursr.domain.model.Course

fun CourseDto.toEntity(): CourseEntity = CourseEntity(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)

fun CourseEntity.toDomain(): Course = Course(
    id = id,
    title = title,
    text = text,
    price = price,
    rate = rate,
    startDate = startDate,
    hasLike = hasLike,
    publishDate = publishDate
)