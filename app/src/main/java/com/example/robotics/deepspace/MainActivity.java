package com.example.robotics.deepspace;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageButton;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    Button BtnTP;
    Button BtnTN;
    Button BtnTnr;
    Button btnTPr;
    Button BtnMP;
    Button BtnMN;
    Button BtnMPr;
    Button BtnMNr;
    Button BtnBN;
    Button BtnBP;
    Button BtnBPr;
    Button BtnBNr;
    AppCompatImageButton S1;


    Intent mServiceIntent = null;

    int tn = 0;
    int tnr = 0;
    int mn = 0;
    int nmr = 0;
    int BNr = 0;
    int Bn = 0;
    int teamNumber = 0;
    String ScouterName = "";
    int finalScore = 0;
    String matchNumber= "";
    TextView txtCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnTP = findViewById(R.id.BtnTP);
        BtnTN = findViewById(R.id.BtnTN);
        BtnTnr = findViewById(R.id.BtnTnr);
        btnTPr = findViewById(R.id.btnTPr);
        BtnMP = findViewById(R.id.BtnMP);
        BtnMN = findViewById(R.id.BtnMN);
        BtnMNr = findViewById(R.id.BtnMNr);
        BtnMPr = findViewById(R.id.BtnMPr);
        BtnBP = findViewById(R.id.BtnBP);
        BtnBN = findViewById(R.id.BtnBN);
        BtnBNr = findViewById(R.id.BtnBNr);
        BtnBPr = findViewById(R.id.BtnBPr);
        S1 = findViewById(R.id.s1);
        BtnTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.tn);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue - 0, 1);
                intCountValue++;
                tn = intCountValue;
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
                tn = intCountValue;
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
                tnr = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        btnTPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.tnr);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue - 0, 1);
                intCountValue++;
                tnr = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.Mn);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue - 0, 1);
                intCountValue++;
                mn = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnMN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.Mn);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.max(intCountValue - 1, 0);
                mn = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnMNr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.nmr);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.max(intCountValue - 1, 0);
                nmr = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnMPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.nmr);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue - 0, 1);
                intCountValue++;
                nmr = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }


        });
        BtnBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.Bn);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue - 0, 1);
                intCountValue++;
                nmr = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.Bn);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.max(intCountValue - 1, 0);
                Bn = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }

        });
        BtnBNr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.BNr);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.max(intCountValue - 1, 0);
                BNr = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));
            }
        });
        BtnBPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtCount = findViewById(R.id.BNr);
                String countValue = txtCount.getText().toString();
                int intCountValue = Integer.parseInt(countValue);
                intCountValue = Math.min(intCountValue - 0, 1);
                intCountValue++;
                BNr = intCountValue;
                txtCount.setText(String.valueOf(intCountValue));

            }
        });
        S1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = new File(""); //BluetoothServerService.getDataDirectory();
                ScouterName = ((TextView) findViewById(R.id.ScouterName)).getText().toString();
                String notes = ((TextView) findViewById(R.id.notes)).getText().toString();
                teamNumber = getIntValue(R.id.teamNumber, "Team Number");
                finalScore = getIntValue(R.id.finalScore, "Final Score");
                int match = getIntValue(R.id.matchNumber, "Match Number");

                if (ScouterName == null || ScouterName.length() == 0 || match == 0 || teamNumber == 0 || finalScore == 0) {
                    Toast.makeText(getApplicationContext(), "Please complete the form before saving", Toast.LENGTH_LONG).show();
                    return;
                }
                Toast toast = Toast.makeText(getApplicationContext(), "Saving", Toast.LENGTH_SHORT);
                toast.show();
                resetapp();
                try {

                    Long myCurrentTimeMillis = System.currentTimeMillis();
                    String timeMillis = Long.toString(myCurrentTimeMillis);
                    File myFile = new File(dir, teamNumber + "_" + match + "_" + timeMillis + ".txt");
                    FileOutputStream fOut = new FileOutputStream(myFile, true);
                    PrintWriter myOutWriter = new PrintWriter(new OutputStreamWriter(fOut));
                    myOutWriter.println("Scouter: " + ScouterName);
                    myOutWriter.println("Team: " + teamNumber);
                    myOutWriter.println("Timestamp: " + (new Date()).getTime());
                    myOutWriter.println("Competition: ");
                    myOutWriter.println("Match: " + match);

                } catch (IOException e) {
                    toast = Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT);
                    Log.e("Exception", "File write failed: " + e.toString());
                }
            }
        });
    }
    public void resetapp(){
        ((CheckBox)findViewById(R.id.LH)).setChecked(false);
        ((TextView)findViewById(R.id.matchNumber)).setText(""+(1+Integer.parseInt(((TextView)findViewById(R.id.matchNumber)).getText().toString())));
        ((TextView)findViewById(R.id.teamNumber)).setText("");
        ((TextView)findViewById(R.id.finalScore)).setText("");
    }
    private int getIntValue(int id, String name) {
        String textValue = "" + ((TextView)findViewById(id)).getText();
        try {
            return Integer.parseInt(textValue);
        } catch (NumberFormatException n) {
            Toast.makeText(getApplicationContext(), name + " must have a numeric value", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }
}




