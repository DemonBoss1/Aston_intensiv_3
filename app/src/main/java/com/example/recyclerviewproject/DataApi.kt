package com.example.recyclerviewproject

import retrofit2.http.GET

interface DataApi {
    @GET("/?count=100&params=LastName,FirstName,Phone")
    suspend fun getData():List<User>
}