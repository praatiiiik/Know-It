package com.example.knowit.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.local.entityTables.SportsNewsTable
import com.example.knowit.data.local.entityTables.TechNewsTable

@Dao
interface GeneralNewsDao {

    //General News
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGeneralNews(generalNewsTable: List<GeneralNewsTable>)

    @Query("Select * from GeneralNewsTable order by generalNewsID ASC")
    fun getGeneralNews() : LiveData<List<GeneralNewsTable>>

    @Query("UPDATE GeneralNewsTable SET imageName =:imageName WHERE generalNewsID = :id")
    fun generalNewsImage(imageName: String?, id: Int?)

    @Query("SELECT  imageName FROM GeneralNewsTable  ")
    fun getGeneralImageName(): List<String?>?

    //BusinessNews
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBusinessNews(businessNewsTable: List<BusinessNewsTable>)

    @Query("Select * from BusinessNewsTable order by businessNewsID ASC")
    fun getBusinessNews() : LiveData<List<BusinessNewsTable>>

    @Query("UPDATE BusinessNewsTable SET imageName =:imageName WHERE businessNewsID = :id")
    fun businessNewsImage(imageName: String?, id: Int?)

    @Query("SELECT  imageName FROM BusinessNewsTable  ")
    fun getBusinessImageName(): List<String?>?

    //SportsNews
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSportsNews(sportsNewsTable: List<SportsNewsTable>)

    @Query("Select * from SportsNewsTable order by sportsNewsID ASC")
    fun getSportsNews() : LiveData<List<SportsNewsTable>>

    @Query("UPDATE SportsNewsTable SET imageName =:imageName WHERE sportsNewsID = :id")
    fun sportsNewsImage(imageName: String?, id: Int?)

    @Query("SELECT  imageName FROM SportsNewsTable  ")
    fun getSportsImageName(): List<String?>?

    //TechNews
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTechNews(techNewsTable: List<TechNewsTable>)

    @Query("Select * from TechNewsTable order by techNewsID ASC")
    fun getTechNews() : LiveData<List<TechNewsTable>>

    @Query("UPDATE TechNewsTable SET imageName =:imageName WHERE techNewsID = :id")
    fun techNewsImage(imageName: String?, id: Int?)

    @Query("SELECT  imageName FROM TechNewsTable  ")
    fun getTechImageName(): List<String?>?

}