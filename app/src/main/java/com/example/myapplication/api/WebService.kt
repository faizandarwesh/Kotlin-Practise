package com.example.myapplication.api

import com.example.myapplication.models.Movie
import retrofit2.Call
import retrofit2.http.GET

interface WebService {

    @GET("")
    fun getMovies(): Call<List<Movie>>
}