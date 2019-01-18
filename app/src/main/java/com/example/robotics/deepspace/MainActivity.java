package com.example.robotics.deepspace;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button BtnTP;
    Button BtnTN;
    Button BtnTnr;
    Button btnTPr;
    int tn=0;
    int tnr=0;
    TextView txtCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnTP = findViewById(R.id.BtnTP);
        BtnTN = findViewById(R.id.BtnTN);
        BtnTnr = findViewById(R.id.BtnTnr);
        btnTPr = findViewById(R.id.btnTPr);

        BtnTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.tn);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue -0, 1);
                intCountValue++;
                tn=intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.tn);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.max(intCountValue - 1, 0);
                tn=intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnTnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.tnr);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.max(intCountValue - 1, 0);
                tnr=intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        btnTPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.tnr);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue -0, 1);
                intCountValue++;
                tnr=intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
    }
}
