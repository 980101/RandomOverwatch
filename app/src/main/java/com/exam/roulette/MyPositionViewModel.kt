package com.exam.roulette

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPositionViewModel: ViewModel() {
    private val _currentButton = MutableLiveData<Int>()

    val currentButton: LiveData<Int>
        get() = _currentButton

    init {
        _currentButton.postValue(R.id.position_tank_radio)
    }

    fun updateToTank() {
        _currentButton.postValue(R.id.position_tank_radio)
    }

    fun updateToDeal() {
        _currentButton.postValue(R.id.position_deal_radio)
    }

    fun updateToHeal() {
        _currentButton.postValue(R.id.position_heal_radio)
    }
}