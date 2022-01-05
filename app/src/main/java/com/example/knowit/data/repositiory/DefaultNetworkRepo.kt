package com.example.knowit.data.repositiory

import android.util.Log
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.local.entityTables.SportsNewsTable
import com.example.knowit.data.local.entityTables.TechNewsTable
import com.example.knowit.data.remote.NetworkService
import com.example.knowit.data.remote.model.Article
import com.example.knowit.data.remote.model.NewsResponse
import com.example.knowit.util.Resource
import retrofit2.Response
import javax.inject.Inject

class DefaultNetworkRepo @Inject constructor(
    private val networkService: NetworkService,
    private val databaseRepo: DatabaseRepo
) : NetworkRepo {


    override suspend fun getGeneralNews() {
        val response = networkService.getGeneralNews()
        when (wrapResponse(response)) {
            is Resource.Success -> {
                val generalNewsList : ArrayList<GeneralNewsTable> = java.util.ArrayList()
                repeat(response.body()?.articles?.size!!){
                    generalNewsList.add(GeneralNewsTable(it,response.body()?.articles?.get(it),null))
                }
                databaseRepo.insertGeneralNews(generalNewsList)
            }
            is Resource.Error -> {
                TODO("")
            }
        }
    }

    override suspend fun getBusinessNews() {
        val response = networkService.getBusinessNews()
        when (wrapResponse(response)) {
            is Resource.Success -> {
                val businessNewsList : ArrayList<BusinessNewsTable> = java.util.ArrayList()
                repeat(response.body()?.articles?.size!!){
                    businessNewsList.add(BusinessNewsTable(it,response.body()?.articles?.get(it),null))
                }
                databaseRepo.insertBusinessNews(businessNewsList)
            }
            is Resource.Error -> {
                TODO("")
            }
        }
    }

    override suspend fun getSportsNews() {
        val response = networkService.getSportsNews()
        when (wrapResponse(response)) {
            is Resource.Success -> {
                val sportsNewsList : ArrayList<SportsNewsTable> = java.util.ArrayList()
                repeat(response.body()?.articles?.size!!){
                    sportsNewsList.add(SportsNewsTable(it,response.body()?.articles?.get(it),null))
                }
                databaseRepo.insertSportsNews(sportsNewsList)
            }
            is Resource.Error -> {
                TODO("")
            }
        }
    }

    override suspend fun getTechNews() {
        val response = networkService.getTechNews()
        when (wrapResponse(response)) {
            is Resource.Success -> {
                val techNewsList : ArrayList<TechNewsTable> = java.util.ArrayList()
                repeat(response.body()?.articles?.size!!){
                    techNewsList.add(TechNewsTable(it,response.body()?.articles?.get(it),null))
                }
                databaseRepo.insertTechNews(techNewsList)
            }
            is Resource.Error -> {
                TODO("")
            }
        }
    }


    override suspend fun wrapResponse(response: Response<NewsResponse>): Resource {
        val result = response.body()
        return if (response.isSuccessful && result != null) {
            Resource.Success(result.articles)
        } else {
            Resource.Error(response.message())
        }
    }
}