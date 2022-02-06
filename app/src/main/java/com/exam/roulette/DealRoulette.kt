package com.exam.roulette

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

class DealRoulette : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deal_roulette)

        val n = getIntent().getIntExtra("num", 16)

        val names = arrayOf("겐지", "둠피스트", "리퍼", "솔저:76", "시메트라", "정크렛", "토르비욘", "트레이서",
        "맥크리", "메이", "바스티온", "애쉬", "에코", "위도우메이커", "파라", "한조")

        val hero: ArrayList<PieEntry> = ArrayList()

        var visit = arrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

        for (i in 0..15) {
            var index:Int

            do {
                index = Random().nextInt(16)
            } while (visit[index] == 1)

            visit[index] = 1

            hero.add(PieEntry(1f, names[index]))
        }

        fun makePieChart() {
            val array: ArrayList<PieEntry> = ArrayList()

            for (i in 0..n-1) {
                array.add(hero[i])
            }

            val pieDataSet = PieDataSet(array, "")
            pieDataSet.setColors(*ColorTemplate.JOYFUL_COLORS)

            pieDataSet.setDrawValues(false)

            pieChart.setEntryLabelColor(Color.BLACK)

            val pieData = PieData(pieDataSet)

            pieChart.setData(pieData)
            pieChart.getDescription().setEnabled(false)
            pieChart.animate()

            pieChart.setRotationEnabled(false)

            pieChart.setTouchEnabled(false)

            val legend: Legend = pieChart.getLegend()
            legend.setEnabled(false)
        }

        makePieChart()

        btn.setOnClickListener {
            spin()
        }
    }

    fun spin() {
        val random = Random()

        val degree = (random.nextInt(361)).toFloat()
        val degreeOld = (degree % 360 + 720)

        val rotateAnim = RotateAnimation(
            degreeOld, degree,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnim.duration = 1000
        rotateAnim.fillAfter = true
        rotateAnim.interpolator = DecelerateInterpolator()

        pieChart.startAnimation(rotateAnim)
    }
}