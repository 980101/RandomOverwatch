package com.exam.roulette

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var n:Int

        //몇 번 버튼이 선택 되었는지 확인하는 변수
        var x:Int = 0

        // 탱커 포지션 button 이 클릭 되었을 떄
        btn_tang.setOnClickListener {

            //입력 숫자 범위 알려주기
            et_num.setHint("1~8 사이의 수를 입력하세요")

            //et_num 안에 값이 있으면 없애주기 --> 조건이 없어도 될 것 같은데?
            if (et_num != null)
                et_num.getText().clear()

            x = 1

            //선택 버튼의 색 변경
            btn_tang.setBackgroundResource(R.drawable.button_checked)
            btn_deal.setBackgroundResource(R.drawable.button_default)
            btn_heal.setBackgroundResource(R.drawable.button_default)
        }

        // 딜러 포지션 button 이 클릭 되었을 때
        btn_deal.setOnClickListener {

            et_num.setHint("1~16 사이의 수를 입력하세요")

            if (et_num != null)
                et_num.getText().clear()

            x = 2

            btn_deal.setBackgroundResource(R.drawable.button_checked)
            btn_tang.setBackgroundResource(R.drawable.button_default)
            btn_heal.setBackgroundResource(R.drawable.button_default)
        }

        // 힐러 포지션 button 이 클릭 되었을 때
        btn_heal.setOnClickListener {
            et_num.setHint("1~7 사이의 수를 입력하세요")

            if (et_num != null)
                et_num.getText().clear()

            x = 3

            btn_heal.setBackgroundResource(R.drawable.button_checked)
            btn_tang.setBackgroundResource(R.drawable.button_default)
            btn_deal.setBackgroundResource(R.drawable.button_default)
        }

        //btn_check 이 클릭 되었을 때
        btn_check.setOnClickListener {

            //포지션이 선택된 경우
            if (x != 0) {

                //아이템 수가 입력 되어 있는지 확인
                if (TextUtils.isEmpty(et_num.getText()) == true) {
                    Toast.makeText(this@MainActivity, "아이템 수를 입력해주세요", Toast.LENGTH_SHORT).show()
                }
                else {
                    n = et_num.getText().toString().toInt()

                    when(x) {
                        1 -> {
                            //가능한 숫자 범위 제한
                            if (0 < n && n < 9) {
                                val intent = Intent(this, tangRoulette::class.java)

                                //plainText로 입력받은 값은 getText로 바꿔줘야하나봄 (아니면 튕김)
                                intent.putExtra("nItem", n)

                                startActivity(intent)
                            }
                            else {
                                //숫자 범위 조건에 맞지 않으면 toast 메시지 띄우기
                                Toast.makeText(this@MainActivity, "1~8 사이의 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
                            }
                        }

                        2 -> {
                            if (0 < n && n < 17) {
                                val intent = Intent(this, DealRoulette::class.java)

                                //plainText로 입력받은 값은 getText로 바꿔줘야하나봄 (아니면 튕김)
                                intent.putExtra("nItem", n)

                                startActivity(intent)
                            }
                            else {
                                Toast.makeText(this@MainActivity, "1~16 사이의 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
                            }
                        }

                        3 -> {
                            if (0 < n && n < 8) {
                                val intent = Intent(this, HealRoulette::class.java)

                                intent.putExtra("nItem", n)

                                startActivity(intent)
                            }
                            else {
                                Toast.makeText(this@MainActivity, "1~7 사이의 숫자를 입력해주세요", Toast.LENGTH_SHORT).show()
                            }
                        }

                        else -> {
                            Toast.makeText(this@MainActivity, "포지션을 선택해주세요", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            //포지션이 선택되지 않은 경우
            else {
                Toast.makeText(this@MainActivity, "포지션을 선택해주세요", Toast.LENGTH_SHORT).show()
            }

        }
    }
}


