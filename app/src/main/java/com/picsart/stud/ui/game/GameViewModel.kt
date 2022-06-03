package com.picsart.stud.ui.game

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picsart.stud.data.model.ResultModel
import com.picsart.stud.data.model.WheelModel
import com.picsart.stud.data.source.local.repo.ResultRepositoryImpl
import com.picsart.stud.utils.game.DEFAULT_SCORE_TO_LEVEL_UP
import com.picsart.stud.utils.game.DEFAULT_WHEEL_SECTOR_VALUE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(private val resultRepositoryImpl: ResultRepositoryImpl) :
    ViewModel() {
    var wheel by mutableStateOf(WheelModel())
        private set

    var playerTurn by mutableStateOf(true)


    var firstScore by mutableStateOf(0)
        private set
    var secondScore by mutableStateOf(0)
        private set
    var level by mutableStateOf(1)
        private set

    var sectorValue by mutableStateOf(DEFAULT_WHEEL_SECTOR_VALUE * level)

    fun rollWheel() {
        if (!wheel.isRoll) {
            wheel = WheelModel(
                degrees = Random.nextInt(0, 361).toFloat(),
                isRoll = true
            )
        }
    }

    fun stopWheel() {
        processScore(wheel.degrees)
        wheel = WheelModel()
    }

    private fun processScore(degrees: Float) = viewModelScope.launch {
        val newScore = when (degrees) {
            in 0F..45F -> 1
            in 45.1F..90F -> 2
            in 90.1F..135F -> 3
            in 135.1F..180F -> 4

            in 180.1F..225F -> 5
            in 225.1F..270F -> 6
            in 270.1F..315F -> 7
            in 315.1F..360F -> 8
            else -> 1
        } * DEFAULT_WHEEL_SECTOR_VALUE * level

        if (playerTurn) {
            firstScore += newScore
        } else {
            secondScore += newScore
        }

        delay(500)

        if (firstScore >= DEFAULT_SCORE_TO_LEVEL_UP * level ||
            secondScore >= DEFAULT_SCORE_TO_LEVEL_UP * level
        ) {
            level++
            sectorValue =
                DEFAULT_WHEEL_SECTOR_VALUE * 2 * level
        }

    }

    fun saveResult() = viewModelScope.launch(Dispatchers.IO) {
        resultRepositoryImpl.saveResult(
            ResultModel(
                firstScore = firstScore,
                secondScore = if (secondScore == 0) -1 else secondScore,
                level = level
            )
        )
    }
}