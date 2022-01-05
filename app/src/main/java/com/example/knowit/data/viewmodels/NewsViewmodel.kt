package com.example.knowit.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowit.data.local.GeneralNewsDao
import com.example.knowit.data.local.entityTables.GeneralNewsTable
import com.example.knowit.data.remote.model.NewsResponse
import com.example.knowit.data.repositiory.DatabaseRepo
import com.example.knowit.data.repositiory.NetworkRepo
import com.example.knowit.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(databaseRepo: DatabaseRepo, private val networkRepo: NetworkRepo) : ViewModel() {


    val generalNewsData = databaseRepo.generalNewsData
    val businessData = databaseRepo.businessNewsData
    val sportsData = databaseRepo.sportsNewsData
    val techNewsData = databaseRepo.techNewsData

    fun getGeneralNews(){
        viewModelScope.launch(Dispatchers.IO) {
            networkRepo.getGeneralNews()
        }
    }

    fun getBusinessNews(){
        viewModelScope.launch(Dispatchers.IO) {
            networkRepo.getBusinessNews()
        }
    }

    fun getSportsNews(){
        viewModelScope.launch(Dispatchers.IO) {
            networkRepo.getSportsNews()
        }
    }

    fun getTechNews(){
        viewModelScope.launch(Dispatchers.IO) {
            networkRepo.getTechNews()
        }
    }

}
