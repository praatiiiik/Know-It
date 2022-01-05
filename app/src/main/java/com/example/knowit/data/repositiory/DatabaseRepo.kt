package com.example.knowit.data.repositiory

import androidx.lifecycle.LiveData
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.local.entityTables.SportsNewsTable
import com.example.knowit.data.local.entityTables.TechNewsTable

interface DatabaseRepo {

    //Insert Functions
    suspend fun insertGeneralNews(data : List<GeneralNewsTable>)
    suspend fun insertBusinessNews(data : List<BusinessNewsTable>)
    suspend fun insertSportsNews(data : List<SportsNewsTable>)
    suspend fun insertTechNews(data : List<TechNewsTable>)

    //Get Functions
    var generalNewsData : LiveData<List<GeneralNewsTable>>
    var businessNewsData : LiveData<List<BusinessNewsTable>>
    var sportsNewsData : LiveData<List<SportsNewsTable>>
    var techNewsData : LiveData<List<TechNewsTable>>


}