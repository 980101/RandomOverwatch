package com.exam.roulette

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

enum class ActionType {
    PLUS, MINUS
}

class MyNumberViewModel : ViewModel() {

    companion object {
        const val TAG: String = "로그"
    }

    // 내부에서 설정하는 자료형은 뮤터블로
    // 변경가능하도록 설정
    private val _currentValue = MutableLiveData<Int>()

    // 변경되지 않는 데이터를 가져올 때 이름을 _ 없이 설정
    // 공개적으로 가져오는 변수는 private이 아닌 public으로 외부에서도 접근가능하도록 설정
    // 하지만 값을 직접 라이브 데이터에 접근하지 않고 뷰모델을 통해 가져올 수 있도록 설정
    val currentValue: LiveData<Int>
        get() = _currentValue

    // 초기값 설정
    init {
        Log.d(TAG, "MyNumberViewModel - 생성자 호출")
        _currentValue.value = 1
    }

    // 뷰모델이 가지고 있는 값을 변경하는 메소드
    fun initValue() {
        _currentValue.value = 1
    }

    fun updateValue(actionType: ActionType, maxValue: Int) {
        var result: Int? = 0

        when(actionType) {
            ActionType.PLUS ->
                result = _currentValue.value?.plus(1)
            ActionType.MINUS ->
                result = _currentValue.value?.minus(1)
        }

        _currentValue.value = checkValue(result, maxValue)
    }

    fun checkValue(value: Int?, maxValue: Int): Int {
        if (value!! > maxValue) return 1
        else if (value < 1) return maxValue
        else return value
    }
}