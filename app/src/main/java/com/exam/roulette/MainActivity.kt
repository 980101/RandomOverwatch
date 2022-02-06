package com.exam.roulette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    lateinit var tv_num : TextView

    var number: Int
    var position: String
    var isChecked: Boolean

    init {
        number = 0
        position = ""
        isChecked = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_num = findViewById(R.id.tv_num)

        // 클릭 이벤트 - 포지션
        val btn_tang:RadioButton = findViewById(R.id.btn_pos_tang)
        btn_tang.setOnClickListener(positionListener)
        val btn_deal:RadioButton = findViewById(R.id.btn_pos_deal)
        btn_deal.setOnClickListener(positionListener)
        val btn_heal:RadioButton = findViewById(R.id.btn_pos_heal)
        btn_heal.setOnClickListener(positionListener)

        // 클릭 이벤트 - 룰렛 아이템 수
        val btn_up:Button = findViewById(R.id.btn_count_up)
        btn_up.setOnClickListener(numberListener)
        val btn_down:Button = findViewById(R.id.btn_count_down)
        btn_down.setOnClickListener(numberListener)

        // 클릭 이벤트 - 확인
        val btn_complete:Button = findViewById(R.id.btn_complete)
        btn_complete.setOnClickListener(completeListener)
    }

    val positionListener = View.OnClickListener { view ->
        initNumber()

        when (view.id) {
            R.id.btn_pos_tang -> {
                setting(8, "tang")
            }
            R.id.btn_pos_deal -> {
                setting(16, "deal")
            }
            R.id.btn_pos_heal -> {
                setting(7, "heal")
            }
        }
    }

    val numberListener = View.OnClickListener { view ->
        if (isChecked) {
            var value = tv_num.text.toString().toInt()

            when (view.id) {
                R.id.btn_count_up -> tv_num.text = checkRange(value + 1).toString()
                R.id.btn_count_down -> tv_num.text = checkRange(value - 1).toString()
            }
        } else {
            Toast.makeText(this, "포지션을 선택하세요!", Toast.LENGTH_SHORT).show()
        }
    }

    val completeListener = View.OnClickListener { view ->
        if (isChecked) {
            lateinit var intent: Intent

            when(position) {
                "tang" -> intent = Intent(this, TangRoulette::class.java)
                "deal" -> intent = Intent(this, DealRoulette::class.java)
                "heal" -> intent = Intent(this, HealRoulette::class.java)
            }

            intent.putExtra("num", tv_num.text.toString().toInt())
            startActivity(intent)

        } else {
            Toast.makeText(this, "설정을 완료해주세요!", Toast.LENGTH_SHORT).show()
        }
    }

    // 포지션 선택 시 아이템 수를 초기화하는 메소드
    fun initNumber() {
        tv_num.text = "1"
        isChecked = true
    }

    // 포지션 선택 시 룰렛 정보를 설정하는 메소드
    fun setting(num:Int, pos:String) {
        number = num
        position = pos
    }

    fun checkRange(value: Int): Int {
        if (value > number) return 1
        else if (value < 1) return number
        else return value
    }
}