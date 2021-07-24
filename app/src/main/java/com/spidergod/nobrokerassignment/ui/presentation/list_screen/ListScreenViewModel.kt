package com.spidergod.nobrokerassignment.ui.presentation.list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spidergod.nobrokerassignment.data.local.entity.NoBrokerEntity
import com.spidergod.nobrokerassignment.repository.NoBrokerRepository
import com.spidergod.nobrokerassignment.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    private val repository: NoBrokerRepository
) : ViewModel() {

    init {
        getData()
    }

    val results = mutableStateOf<Resource<List<NoBrokerEntity>>?>(null)

    fun getData() {
        viewModelScope.launch {
            repository.getDataFromNoBroker().collect {
                results.value = it
            }
        }
    }

    val currentSearchQuery = mutableStateOf("")

}