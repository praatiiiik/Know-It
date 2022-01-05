package com.example.knowit.data.local.entityTables

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.knowit.data.remote.model.Article

@Entity(tableName = "BusinessNewsTable")
data class BusinessNewsTable(var id: Int?, @Embedded var article: Article?, var imageName : String?) {
    @PrimaryKey(autoGenerate = true)
    var businessNewsID : Int? = id
}