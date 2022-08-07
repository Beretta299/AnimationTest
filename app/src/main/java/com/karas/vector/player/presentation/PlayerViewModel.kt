package com.karas.vector.player.presentation

import android.graphics.Color
import androidx.lifecycle.ViewModel

class PlayerViewModel : ViewModel() {
    private val colorsList = listOf(Color.BLACK, Color.WHITE, Color.RED, Color.BLUE, Color.YELLOW)


    fun getColorsList(): List<Int> = colorsList
}