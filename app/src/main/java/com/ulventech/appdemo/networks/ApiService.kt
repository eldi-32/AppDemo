package com.ulventech.appdemo.networks

import com.ulventech.appdemo.models.RepoItems
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{user}/repos")
    fun getRepos(@Path("user") user:String): Call<List<RepoItems>>
}