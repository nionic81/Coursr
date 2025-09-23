package com.nionic.coursr.di

import com.nionic.coursr.data.local.dao.CourseDao
import com.nionic.coursr.data.local.dao.UserDao
import com.nionic.coursr.data.local.db.CoursesDataBase
import com.nionic.coursr.data.local.db.DbProvider
import com.nionic.coursr.data.repository.CourseRepositoryImpl
import com.nionic.coursr.domain.repository.CourseRepository
import com.nionic.coursr.domain.useCase.CourseUseCase
import com.nionic.coursr.domain.useCase.FavUseCase
import com.nionic.coursr.domain.useCase.LoadCoursesUseCase
import com.nionic.coursr.domain.useCase.ToggleFavUseCase
import com.nionic.coursr.presentation.viewModel.CourseViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    single { DbProvider.getDB(androidContext()) }
    single<CourseDao> { get<CoursesDataBase>().courseDao() }
    single<UserDao> { get<CoursesDataBase>().userDao() }
    single<CourseRepository> { CourseRepositoryImpl(get()) }
}

val domainModule = module {
    factory { CourseUseCase(get()) }
    factory { FavUseCase(get()) }
    factory { LoadCoursesUseCase(get()) }
    factory { ToggleFavUseCase(get()) }
}

val viewModelModule = module {
    viewModel { CourseViewModel() }
}

val appModules = listOf(
    dataModule,
    domainModule,
    viewModelModule
)