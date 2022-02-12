package com.exam.roulette

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class PositionType {
    TANG, DEAL, HEAL
}

class MyPositionViewModel: ViewModel() {
    private val _currentButton = MutableLiveData<Int>()

    val currentButton: LiveData<Int>
        get() = _currentButton

    init {
        _currentButton.postValue(R.id.position_tang_radio)
    }

    fun updateButton(positionType: PositionType) {
        when(positionType) {
            PositionType.TANG ->
                _currentButton.postValue(R.id.position_tang_radio)
            PositionType.DEAL ->
                _currentButton.postValue(R.id.position_deal_radio)
            PositionType.HEAL ->
                _currentButton.postValue(R.id.position_heal_radio)
        }
    }
}