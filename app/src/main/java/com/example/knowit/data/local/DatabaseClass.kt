package com.example.knowit.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.local.entityTables.SportsNewsTable
import com.example.knowit.data.local.entityTables.TechNewsTable

@Database(
    entities = [GeneralNewsTable::class, BusinessNewsTable::class, SportsNewsTable::class, TechNewsTable::class], // Tell the database the entries will hold data of this type
    version = 1,
    exportSchema = false
)
abstract class DatabaseClass : RoomDatabase(){

    abstract fun getNewsDao():GeneralNewsDao

}

//GeneralNewsTable::class,BusinessNewsTable::class,SportsNewsTable::class,TechNewsTable::class,