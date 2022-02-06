package com.sohee.mynewapplication.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.sohee.mynewapplication.data.GgdDatabase
import com.sohee.mynewapplication.model.Ggd
import com.sohee.mynewapplication.repository.GgdRespository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GgdViewModel (application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Ggd>>
    private val repository : GgdRespository

    init {
        val userDao = GgdDatabase.getDatabase(application)!!.ggdDao()
        repository = GgdRespository(userDao)
        readAllData = repository.readAllData.asLiveData()
    }

    fun addUser(user : Ggd){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : Ggd){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user : Ggd){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<Ggd>> {
        return repository.searchDatabase(searchQuery).asLiveData()
    }

    // ViewModel에 파라미터를 넘기기 위해서, 파라미터를 포함한 Factory 객체를 생성하기 위한 클래스
    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return GgdViewModel(application) as T
        }
    }
}