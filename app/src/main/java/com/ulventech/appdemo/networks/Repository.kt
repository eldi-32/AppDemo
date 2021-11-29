package com.ulventech.appdemo.networks

class Repository (private val apiService: ApiService) {

     fun getRepos(user:String) = apiService.getRepos(user)
}