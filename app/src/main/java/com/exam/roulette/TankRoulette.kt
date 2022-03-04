package com.exam.roulette

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_tang_roulette.*
import java.util.*
import kotlin.collections.ArrayList

class TankRoulette : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tang_roulette)

        //mainActivity에서 item 개수를 받아온다
        val n = getIntent().getIntExtra("num", 8)

        //string 배열
        val names = arrayOf("디바", "라인하르트", "레킹볼", "로드호그", "윈스턴", "자리야", "시그마", "오리사")

        //PieEntry 형 데이터가 들어갈 배열
        //val hero: MutableList<PieEntry> = mutableListOf()
        val hero: ArrayList<PieEntry> = ArrayList()

        //방문 기록을 남기는 배열
        var visit = arrayOf(0, 0, 0, 0, 0, 0, 0, 0)

        //hero 배열 생성
        for (i in 0..7) {
            var index:Int

            //중복 확인
            do {
                index = Random().nextInt(8)
            } while (visit[index] == 1)

            visit[index] = 1

            //PieChart 형 (나타낼 값f, "값의 이름") 데이터 추가
            hero.add(PieEntry(1f, names[index]))
        }

        fun makePieChart() {
            val array: ArrayList<PieEntry> = ArrayList()

            for (i in 0..n-1) {
                array.add(hero[i])
            }

            //PieChart 데이터 커스텀
            val pieDataSet: PieDataSet = PieDataSet(array, "")
            pieDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)
            //매개변수 value 값 안보이게 하는 법 **
            pieDataSet.setDrawValues(false)
            //label 색 변경
            //val labelColor:Int = Color.parseColor("#000000")
            pieChart.setEntryLabelColor(Color.BLACK)
            //PieChart 데이터 수집
            val pieData: PieData = PieData(pieDataSet)

            //pieChart에 데이터 넣기
            pieChart.setData(pieData)
            pieChart.getDescription().setEnabled(false)
            pieChart.animate()
            //pieChart 회전 안되게
            pieChart.setRotationEnabled(false)
            //pieChart item 선택 안되게
            pieChart.setTouchEnabled(false)

            //pieChart에 테두리 그리기

            //legend 삭제 **
            val legend: Legend = pieChart.getLegend()
            legend.setEnabled(false)
        }

        makePieChart()

        btn.setOnClickListener {
            //버튼 클릭 시
            spin()
        }
    }

    fun spin() {
        val random = Random()

        // we calculate random angle for rotation of our wheel
        val degree = (random.nextInt(361)).toFloat()
        val degreeOld = (degree % 360 + 720).toFloat()

        // rotation effect on the center of the wheel
        val rotateAnim: RotateAnimation = RotateAnimation(
            degreeOld, degree,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnim.duration = 1000
        rotateAnim.fillAfter = true
        rotateAnim.interpolator = DecelerateInterpolator()

        // we start the animation
        pieChart.startAnimation(rotateAnim)
    }

}