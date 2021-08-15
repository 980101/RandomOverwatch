package com.exam.roulette;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static int number = 0;

    class ItemBtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView tvNum = findViewById(R.id.tv_num);
            int value = Integer.parseInt(tvNum.getText().toString());

            switch (view.getId()) {
                case R.id.btn_up :
                    tvNum.setText(String.valueOf(checkRange(value + 1)));
                    break;
                case R.id.btn_down :
                    tvNum.setText(String.valueOf(checkRange(value - 1)));
                    break;
            }
        }
    }

    class PosBtnOnClickListener implements View.OnClickListener {

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_tang :
                    number = 8;
                    break;
                case R.id.btn_deal :
                    number = 16;
                    break;
                case R.id.btn_heal :
                    number = 7;
                    break;
            }
        }
    }

    private static int checkRange(int value) {
        if (value < 1) {
            return value = number;
        } else if (value > number) {
            return value = 1;
        } else {
            return value;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ItemBtnOnClickListener onItemClickListener = new ItemBtnOnClickListener();

        Button btnUp = findViewById(R.id.btn_up);
        btnUp.setOnClickListener(onItemClickListener);
        Button btnDown = findViewById(R.id.btn_down);
        btnDown.setOnClickListener(onItemClickListener);

        PosBtnOnClickListener onPosClickListener = new PosBtnOnClickListener();

        RadioButton btnTang = findViewById(R.id.btn_tang);
        btnTang.setOnClickListener(onPosClickListener);
        RadioButton btnDeal = findViewById(R.id.btn_deal);
        btnDeal.setOnClickListener(onPosClickListener);
        RadioButton btnHeal = findViewById(R.id.btn_heal);
        btnHeal.setOnClickListener(onPosClickListener);
    }

}


