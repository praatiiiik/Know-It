package com.example.knowit.data.repositiory

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.knowit.data.local.GeneralNewsDao
import com.example.knowit.data.local.entityTables.BusinessNewsTable
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.local.entityTables.SportsNewsTable
import com.example.knowit.data.local.entityTables.TechNewsTable
import com.example.knowit.util.ConvertToBitmap
import com.example.knowit.util.ImageStorageManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultDatabaseRepo @Inject constructor(
    private val dao: GeneralNewsDao,
    val context: Context
) : DatabaseRepo {


    //Inserting Data
    override suspend fun insertGeneralNews(data: List<GeneralNewsTable>) {
        val imageData = dao.getGeneralImageName()
        coroutineScope {
            launch(Dispatchers.Default) {
                repeat(imageData!!.size){
                    if(!imageData[it].isNullOrEmpty()){
                        ImageStorageManager.deleteImageFromInternalStorage(context,imageData[it]!!)
                    }
                }
            }
        }
        dao.insertGeneralNews(data)
        val arrayList: ArrayList<String?> = ArrayList()
        repeat(data.size) {
            arrayList.add(data[it].article?.urlToImage)
        }
        updateGeneralNewsImage(arrayList)
    }

    override suspend fun insertBusinessNews(data: List<BusinessNewsTable>) {
        val imageData = dao.getBusinessImageName()
        coroutineScope {
            launch(Dispatchers.Default) {
                repeat(imageData!!.size){
                    if(!imageData[it].isNullOrEmpty()){
                        ImageStorageManager.deleteImageFromInternalStorage(context,imageData[it]!!)
                    }
                }
            }
        }
        dao.insertBusinessNews(data)
        val arrayList: ArrayList<String?> = ArrayList()
        repeat(data.size) {
            arrayList.add(data[it].article?.urlToImage)
        }
        updateBusinessNewsImage(arrayList)
    }

    override suspend fun insertSportsNews(data: List<SportsNewsTable>) {
        val imageData = dao.getSportsImageName()
        coroutineScope {
            launch(Dispatchers.Default) {
                repeat(imageData!!.size){
                    if(!imageData[it].isNullOrEmpty()){
                        ImageStorageManager.deleteImageFromInternalStorage(context,imageData[it]!!)
                    }
                }
            }
        }
        dao.insertSportsNews(data)
        val arrayList: ArrayList<String?> = ArrayList()
        repeat(data.size) {
            arrayList.add(data[it].article?.urlToImage)
        }
        updateSportsNewsImage(arrayList)
    }

    override suspend fun insertTechNews(data: List<TechNewsTable>) {
        val imageData = dao.getTechImageName()
        coroutineScope {
            launch(Dispatchers.Default) {
                repeat(imageData!!.size){
                    if(!imageData[it].isNullOrEmpty()){
                        ImageStorageManager.deleteImageFromInternalStorage(context,imageData[it]!!)
                    }
                }
            }
        }
        dao.insertTechNews(data)
        val arrayList: ArrayList<String?> = ArrayList()
        repeat(data.size) {
            arrayList.add(data[it].article?.urlToImage)
        }
        updateTechNewsImage(arrayList)
    }

    //Getting data
    override var generalNewsData: LiveData<List<GeneralNewsTable>> = dao.getGeneralNews()

    override var businessNewsData: LiveData<List<BusinessNewsTable>> = dao.getBusinessNews()

    override var sportsNewsData: LiveData<List<SportsNewsTable>> = dao.getSportsNews()

    override var techNewsData: LiveData<List<TechNewsTable>> = dao.getTechNews()

    //Inserting Image
    private suspend fun updateGeneralNewsImage(data: List<String?>) {
        coroutineScope {
            launch(Dispatchers.Default) {
                repeat(data.size) {
                    val imageName = ConvertToBitmap.getBitmap(data[it], context)
                    dao.generalNewsImage(imageName, it)
                }
            }
        }
    }

    private suspend fun updateBusinessNewsImage(data: List<String?>) {
        repeat(data.size) {
            val imageName = ConvertToBitmap.getBitmap(data[it], context)
            dao.businessNewsImage(imageName, it)
        }
    }

    private suspend fun updateSportsNewsImage(data: List<String?>) {
        repeat(data.size) {
            if (!data[it].isNullOrEmpty()) {
                val imageName = ConvertToBitmap.getBitmap(data[it], context)
                dao.sportsNewsImage(imageName, it)
            }
        }
    }

    private suspend fun updateTechNewsImage(data: List<String?>) {
        repeat(data.size) {
            val imageName = ConvertToBitmap.getBitmap(data[it], context)
            dao.techNewsImage(imageName, it)
        }
    }


}