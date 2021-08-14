package com.exam.roulette;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    class BtnOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            TextView tvNum = findViewById(R.id.tv_num);
            int value = Integer.parseInt(tvNum.getText().toString());
            switch (view.getId()) {
                case R.id.btn_up :
                    tvNum.setText(String.valueOf(value + 1));
                    break;
                case R.id.btn_down :
                    tvNum.setText(String.valueOf(value - 1));
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnOnClickListener onClickListener = new BtnOnClickListener();

        Button btnUp = findViewById(R.id.btn_up);
        btnUp.setOnClickListener(onClickListener);
        Button btnDown = findViewById(R.id.btn_down);
        btnDown.setOnClickListener(onClickListener);
    }

}


