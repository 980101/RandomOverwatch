package com.exam.roulette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import com.exam.roulette.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var myNumberView: MyNumberViewModel
    lateinit var myPositionView: MyPositionViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // 뷰모델 프로바이더를 통해 뷰모델 가져오기
        // 라이프사이클을 가지고 있는 녀석을 넣어줌 즉 자기 자신
        // 우리가 가져오고 싶은 뷰모델 클래스를 넣어서 뷰모델을 가져오기
        myNumberView = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        myPositionView = ViewModelProvider(this).get(MyPositionViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙한다.
        myPositionView.currentButton.observe(this, Observer {
            // radio group이 선택하고 있는 버튼을 변경
            myNumberView.initValue()
            myNumberView.updateRange(it)
        })

        binding.lifecycleOwner = this
        binding.number = myNumberView
        binding.position = myPositionView

    }

    fun loadRoulette(view: View) {

        val pos = myPositionView.currentButton.value
        val num = myNumberView.currentValue.value

        lateinit var intent: Intent

        when(pos) {
            R.id.position_tang_radio -> intent = Intent(this, TangRoulette::class.java)
            R.id.position_deal_radio -> intent = Intent(this, DealRoulette::class.java)
            R.id.position_heal_radio -> intent = Intent(this, HealRoulette::class.java)
        }

        intent.putExtra("num", num)
        startActivity(intent)
    }
}