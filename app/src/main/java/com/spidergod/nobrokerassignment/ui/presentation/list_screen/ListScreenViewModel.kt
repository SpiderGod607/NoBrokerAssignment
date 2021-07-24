package com.spidergod.nobrokerassignment.ui.presentation.list_screen

import androidx.lifecycle.ViewModel
import com.spidergod.nobrokerassignment.repository.NoBrokerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListScreenViewModel @Inject constructor(
    repository: NoBrokerRepository
) : ViewModel() {
}