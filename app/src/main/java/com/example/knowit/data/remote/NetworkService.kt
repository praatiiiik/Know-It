package com.example.knowit.data.remote

import com.example.knowit.data.remote.model.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {

    @GET(EndPoints.BUSINESS)
    suspend fun getBusinessNews(): Response<NewsResponse>

    @GET(EndPoints.GENERAL)
    suspend fun getGeneralNews(): Response<NewsResponse>

    @GET(EndPoints.SPORTS)
    suspend fun getSportsNews(): Response<NewsResponse>

    @GET(EndPoints.TECH)
    suspend fun getTechNews(): Response<NewsResponse>

}