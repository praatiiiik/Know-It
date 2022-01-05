package com.example.knowit.data.repositiory

import androidx.lifecycle.LiveData
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.remote.model.NewsResponse
import com.example.knowit.util.Resource
import retrofit2.Response

interface NetworkRepo {

    suspend fun getGeneralNews()
    suspend fun getBusinessNews()
    suspend fun getSportsNews()
    suspend fun getTechNews()
    suspend fun wrapResponse(response: Response<NewsResponse>) : Resource

}