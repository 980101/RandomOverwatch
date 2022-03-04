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

        myNumberView = ViewModelProvider(this).get(MyNumberViewModel::class.java)
        myPositionView = ViewModelProvider(this).get(MyPositionViewModel::class.java)

        myPositionView.currentButton.observe(this, Observer {
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
            R.id.position_tank_radio -> intent = Intent(this, TankRoulette::class.java)
            R.id.position_deal_radio -> intent = Intent(this, DealRoulette::class.java)
            R.id.position_heal_radio -> intent = Intent(this, HealRoulette::class.java)
        }

        intent.putExtra("num", num)
        startActivity(intent)
    }
}