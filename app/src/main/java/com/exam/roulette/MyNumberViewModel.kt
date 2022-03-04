package com.exam.roulette

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyNumberViewModel : ViewModel() {

    private val _currentValue = MutableLiveData<Int>()
    private val _maxValue = MutableLiveData<Int>()

    val currentValue: LiveData<Int>
        get() = _currentValue

    val maxValue: LiveData<Int>
        get() = _maxValue

    init {
        _currentValue.value = 1
        _maxValue.value = 8
    }

    fun initValue() {
        _currentValue.value = 1
    }

    fun updateRange(id: Int) {
        when(id) {
            R.id.position_tank_radio ->
                _maxValue.value = 8
            R.id.position_deal_radio ->
                _maxValue.value = 16
            R.id.position_heal_radio ->
                _maxValue.value = 7
        }
    }

    fun increase() {
        val result = _currentValue.value?.plus(1)
        _currentValue.value = checkRange(result, _maxValue.value)
    }

    fun decrease() {
        val result = _currentValue.value?.minus(1)
        _currentValue.value = checkRange(result, _maxValue.value)
    }

    fun checkRange(value: Int?, maxValue: Int?): Int {
        if (value!! > maxValue!!) return 1
        else if (value < 1) return maxValue
        else return value
    }
}