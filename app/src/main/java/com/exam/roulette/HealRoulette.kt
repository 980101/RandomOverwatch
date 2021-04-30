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

class HealRoulette : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heal_roulette)

        val n = getIntent().getIntExtra("nItem", 7)

        val names = arrayOf("아나", "메르시", "젠야타", "루시우", "브리기테", "모이라", "바티스트")

        val hero: ArrayList<PieEntry> = ArrayList()

        var visit = arrayOf(0, 0, 0, 0, 0, 0, 0)

        for (i in 0..6) {
            var index:Int

            do {
                index = Random().nextInt(7)
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

            val pieData= PieData(pieDataSet)

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