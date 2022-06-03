package com.picsart.stud.ui.game.menu.result

import androidx.lifecycle.ViewModel
import com.picsart.stud.data.source.local.repo.ResultRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MenuResultsViewModel @Inject constructor(
    private val resultRepositoryImpl: ResultRepositoryImpl
) : ViewModel() {
    val results = resultRepositoryImpl.getResults()
}