package com.nionic.coursr.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    fun getCourses(@Url url: String): Call<CoursesResponse>
}