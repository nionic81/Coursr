package com.nionic.coursr.data.mapper

import com.nionic.coursr.data.local.entity.CourseEntity
import com.nionic.coursr.data.local.entity.UserEntity
import com.nionic.coursr.data.remote.CourseDto
import com.nionic.coursr.domain.model.Course
import com.nionic.coursr.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    name = name,
    email = email
)

fun UserEntity.toDomain(): User = User(
    id = id,
    name = name,
    email = email
)