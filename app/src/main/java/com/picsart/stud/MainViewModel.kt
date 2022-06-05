package com.picsart.stud

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.picsart.stud.ui.navigation.NavKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    var isrtstuDataDaomding by mutableStateOf(true)
    var route by mutableStateOf(NavKeys.Init.route)
}