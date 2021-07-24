package com.spidergod.nobrokerassignment.ui.presentation.list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.spidergod.nobrokerassignment.data.local.entity.NoBrokerEntity
import com.spidergod.nobrokerassignment.repository.NoBrokerRepository
import com.spidergod.nobrokerassignment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val repository: NoBrokerRepository
) : ViewModel() {
    val responseData: LiveData<Resource<List<NoBrokerEntity>>>

    init {
        responseData = getData()
    }


    val currentSearchQuery = mutableStateOf("")

     fun getData(): LiveData<Resource<List<NoBrokerEntity>>> {
        return repository.getDataFromNoBroker().asLiveData()
    }

}