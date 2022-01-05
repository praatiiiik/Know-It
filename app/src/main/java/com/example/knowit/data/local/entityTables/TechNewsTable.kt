package com.example.knowit.data.local.entityTables

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.knowit.data.remote.model.Article

@Entity(tableName = "TechNewsTable")
class TechNewsTable(var id: Int?,@Embedded var article: Article?, var imageName : String?) {
    @PrimaryKey(autoGenerate = true)
    var techNewsID : Int? = id
}