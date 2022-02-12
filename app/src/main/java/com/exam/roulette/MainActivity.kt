package com.exam.roulette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val TAG: String = "로그"
    }

    lateinit var myNumberView: MyNumberViewModel
    lateinit var myPositionView: MyPositionViewModel

    var number: Int
    var position: String

    init {
        number = 8
        position = "tang"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 뷰모델 프로바이더를 통해 뷰모델 가져오기
        // 라이프사이클을 가지고 있는 녀석을 넣어줌 즉 자기 자신
        // 우리가 가져오고 싶은 뷰모델 클래스를 넣어서 뷰모델을 가져오기
        myNumberView = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        myPositionView = ViewModelProvider(this).get(MyPositionViewModel::class.java)

        // 뷰모델이 가지고 있는 값의 변경사항을 관찰할 수 있는 라이브 데이터를 옵저빙한다.
        myNumberView.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - myNumberViewModel - currentValue 라이브 데이터 값 변경 : $it")
            number_textview.text = it.toString()
        })

        myPositionView.currentButton.observe(this, Observer {
            // radio group이 선택하고 있는 버튼을 변경
            position_radioGroup.check(it)
        })

        // 클릭 이벤트 - 포지션
        position_tang_radio.setOnClickListener(positionListener)
        position_deal_radio.setOnClickListener(positionListener)
        position_heal_radio.setOnClickListener(positionListener)

        // 클릭 이벤트 - 룰렛 아이템 수
        count_up_btn.setOnClickListener(numberListener)
        count_down_btn.setOnClickListener(numberListener)

        // 클릭 이벤트 - 확인
        btn_complete.setOnClickListener(completeListener)
    }

    val positionListener = View.OnClickListener { view ->
        initNumber()

        when (view.id) {
            R.id.position_tang_radio -> {
                setting(8, "tang")
                myPositionView.updateButton(positionType = PositionType.TANG)
            }
            R.id.position_deal_radio -> {
                setting(16, "deal")
                myPositionView.updateButton(positionType = PositionType.DEAL)
            }
            R.id.position_heal_radio -> {
                setting(7, "heal")
                myPositionView.updateButton(positionType = PositionType.HEAL)
            }
        }
    }

    val numberListener = View.OnClickListener { view ->
        // 뷰모델에 라이브데이터 값을 변경하는 메소드 실행
        when (view.id) {
            R.id.count_up_btn ->
                myNumberView.updateValue(actionType = ActionType.PLUS, number)
            R.id.count_down_btn ->
                myNumberView.updateValue(actionType = ActionType.MINUS, number)
        }
    }

    val completeListener = View.OnClickListener { view ->
        lateinit var intent: Intent

        when(position) {
            "tang" -> intent = Intent(this, TangRoulette::class.java)
            "deal" -> intent = Intent(this, DealRoulette::class.java)
            "heal" -> intent = Intent(this, HealRoulette::class.java)
        }

        intent.putExtra("num", number_textview.text.toString().toInt())
        startActivity(intent)
    }

    // 포지션 선택 시 아이템 수를 초기화하는 메소드
    fun initNumber() {
        number_textview.text = "1"
    }

    // 포지션 선택 시 룰렛 정보를 설정하는 메소드
    fun setting(num:Int, pos:String) {
        number = num
        position = pos
    }
}