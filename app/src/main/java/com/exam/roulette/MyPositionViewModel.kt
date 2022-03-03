package com.exam.roulette

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class PositionType {
    TANG, DEAL, HEAL
}

class MyPositionViewModel: ViewModel() {
    private val _currentButton = MutableLiveData<Int>()
    private val _pos = MutableLiveData<String>()

    val currentButton: LiveData<Int>
        get() = _currentButton

    val pos: LiveData<String>
        get() = _pos

    init {
        _currentButton.postValue(R.id.position_tang_radio)
        _pos.value = "tang"
    }

    fun updatePosition(positionType: PositionType) {
        when(positionType) {
            PositionType.TANG -> {
                _currentButton.postValue(R.id.position_tang_radio)
                _pos.value = "Tang"
            }
            PositionType.DEAL -> {
                _currentButton.postValue(R.id.position_deal_radio)
                _pos.value = "Deal"
            }
            PositionType.HEAL -> {
                _currentButton.postValue(R.id.position_heal_radio)
                _pos.value = "Heal"
            }
        }
    }

    fun checkTang() {
        updatePosition(PositionType.TANG)
    }

    fun checkDeal() {
        updatePosition(PositionType.DEAL)
    }

    fun checkHeal() {
        updatePosition(PositionType.HEAL)
    }
}