package com.example.robotics.deepspace;

import android.app.ActionBar;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher;


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
    Button StandStorm;
    Button Teleop;
    Button Field;
    boolean inTeleop;
    boolean color = false;
    boolean inTeleopColor;
    boolean SandButVBackround = false;
    boolean SandButABackround = false;

    ImageButton S1;
    ImageButton Ns;
    ImageView Timage;
    ImageView TeamImage;
    TextView Left;
    TextView Center;
    TextView Right;
    TextView Front;
    TextView Left2;
    TextView Center2;
    TextView Right2;
    TextView Front2;
    TextView TeamNumber;


    CheckBox RTopleft;
    CheckBox RTopleft1;
    CheckBox RTopright;
    CheckBox RTopright1;
    CheckBox RmiddleLeft;
    CheckBox RmiddleLeft1;
    CheckBox RmiddleRight;
    CheckBox RmiddleRight1;
    CheckBox RBottoml;
    CheckBox RBottomRight;
    CheckBox RBottomleft1;
    CheckBox RBottomRight1;
    CheckBox LH;
    CheckBox CLH;
    CheckBox RLH;
    CheckBox FLH;
    CheckBox LRH;
    CheckBox CRH;
    CheckBox RRH;
    CheckBox FRH;
    CheckBox LB;
    CheckBox CLB;
    CheckBox RLB;
    CheckBox FLB;
    CheckBox LRb;
    CheckBox CRB;
    CheckBox RRB;
    CheckBox FRB;
    CheckBox dbbp;
    CheckBox fo;
    CheckBox bd;
    CheckBox ci;
    CheckBox Teleop_in;
    CheckBox RedAlliance;
    CheckBox BlueAlliance;


    Intent mServiceIntent = null;

    int tn = 0;
    //int sandBallA = 0;
    int tnr = 0;
    int mn = 0;
    int nmr = 0;
    int BNr = 0;
    int Bn = 0;
    int sandstormscore = 0;
    int hatchScore = 0;
    int teleopscore = 0;
    int teamNumber = 0;
    String ScouterName = "";
    int finalScore = 0;
    String matchNumber = "";
    String wlt = "";
    String EndGame = "";
    String StartingP ="";
    String sandBallA ="";
    String sandHatchA ="";

    String speed = "";
    TextView txtCount;

    int increaseShipBallScore(int id) {
        TextView view = findViewById(id);
        String fieldValue = view.getText().toString();
        int currentValue = Integer.parseInt(fieldValue);
        int newValue = Math.min(1, currentValue);
        newValue++;
        view.setText(String.valueOf(newValue));
        return newValue;
    }

    int decreaseShipBallScore(int id) {
        TextView txtCount = findViewById(id);
        String countValue = txtCount.getText().toString();
        int intCountValue = Integer.parseInt(countValue);
        int oldValue = intCountValue;
        intCountValue = Math.max(intCountValue - 1, 0);
        txtCount.setText(String.valueOf(intCountValue));
        return intCountValue;
    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            hideSystemUI();
//        }
//    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        Field = findViewById(R.id.Field);
        StandStorm = findViewById(R.id.StandStorm);
        Teleop = findViewById(R.id.Teleop);
        Teleop = findViewById(R.id.Teleop);
        RTopleft = findViewById(R.id.RTopleft);
        RTopleft1 = findViewById(R.id.RTopleft1);
        RTopright = findViewById(R.id.RTopright);
        RTopright1 = findViewById(R.id.RTopright1);
        RmiddleRight = findViewById(R.id.RmiddleRight);
        RmiddleRight1 = findViewById(R.id.RmiddleRight1);
        RmiddleLeft = findViewById(R.id.RmiddleLeft);
        RmiddleLeft1 = findViewById(R.id.Rmiddleleft1);
        Left = findViewById(R.id.Left);
        Center = findViewById(R.id.Center);
        Right = findViewById(R.id.Right);
        Front = findViewById(R.id.Front);
        Left2 = findViewById(R.id.left2);
        Center2 = findViewById(R.id.center);
        Right2 = findViewById(R.id.right2);
        Front2 = findViewById(R.id.front);
        TeamNumber = findViewById(R.id.teamNumber);
        TeamImage = findViewById(R.id.TeamImage);
        ((TextView) findViewById(R.id.notes)).setText("");

        RBottoml = findViewById(R.id.RBottoml);
        RBottomRight = findViewById(R.id.RBottomRight);
        RBottomleft1 = findViewById(R.id.RBottomleft1);
        RBottomRight1 = findViewById(R.id.RBottomRight1);
        LH = findViewById(R.id.LH);
        CLH = findViewById(R.id.CLH);
        RLH = findViewById(R.id.RLH);
        FLH = findViewById(R.id.FLH);
        LRH = findViewById(R.id.LRH);
        CRH = findViewById(R.id.CRH);
        RRH = findViewById(R.id.RRH);
        FRH = findViewById(R.id.FRH);
        LB = findViewById(R.id.LB);
        CLB = findViewById(R.id.CLB);
        RLB = findViewById(R.id.RLB);
        FLB = findViewById(R.id.FLB);
        RRB = findViewById(R.id.RRB);
        CRB = findViewById(R.id.CRB);
        LRb = findViewById(R.id.LRB);
        FRB = findViewById(R.id.FRB);
        RedAlliance = findViewById(R.id.RedAlliance);
        BlueAlliance = findViewById(R.id.blueAlliance);

        fo = findViewById(R.id.fo);
        bd = findViewById(R.id.bd);
        ci = findViewById(R.id.ci);
        dbbp = findViewById(R.id.dbbp);
        ((CheckBox) findViewById(R.id.blueAlliance)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    findViewById(R.id.main).setBackgroundColor(Color.rgb(100, 149, 237));
                    ((CheckBox) findViewById(R.id.RedAlliance)).setChecked(false);
                } else {
                    if (((CheckBox) findViewById(R.id.RedAlliance)).isChecked() == false) {
                        findViewById(R.id.main).setBackgroundColor(Color.rgb(255, 255, 255));
                    }
                }
            }
        });
        ((CheckBox) findViewById(R.id.RedAlliance)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    findViewById(R.id.main).setBackgroundColor(Color.rgb(220, 20, 60));
                    ((CheckBox) findViewById(R.id.blueAlliance)).setChecked(false);
                } else {
                    if (((CheckBox) findViewById(R.id.blueAlliance)).isChecked() == false) {
                        findViewById(R.id.main).setBackgroundColor(Color.rgb(255, 255, 255));
                    }
                }
            }
        });

        //sets text size
        Teleop.setTextSize(14 * getResources().getDisplayMetrics().density);
        StandStorm.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnTP.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnTN.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnMP.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnMN.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnBP.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnBN.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnTnr.setTextSize(20 * getResources().getDisplayMetrics().density);
        btnTPr.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnMPr.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnMNr.setTextSize(20 * getResources().getDisplayMetrics().density);
        BtnBPr.setTextSize(14 * getResources().getDisplayMetrics().density);
        BtnBNr.setTextSize(20 * getResources().getDisplayMetrics().density);
        Left.setTextSize(20 * getResources().getDisplayMetrics().density);
        Center.setTextSize(20 * getResources().getDisplayMetrics().density);
        Right.setTextSize(20 * getResources().getDisplayMetrics().density);
        Front.setTextSize(20 * getResources().getDisplayMetrics().density);
        Left2.setTextSize(20 * getResources().getDisplayMetrics().density);
        Center2.setTextSize(20 * getResources().getDisplayMetrics().density);
        Right2.setTextSize(20 * getResources().getDisplayMetrics().density);
        Front2.setTextSize(20 * getResources().getDisplayMetrics().density);


        inTeleop = false;
        inTeleopColor = false;

        Ns = findViewById(R.id.Ns);
        S1 = findViewById(R.id.s1);
        BtnTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tn = increaseShipBallScore(R.id.tn);
            }
        });
        BtnTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tn = decreaseShipBallScore(R.id.tn);
            }
        });
        BtnTnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tnr = decreaseShipBallScore(R.id.tnr);
            }
        });
        btnTPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tnr = increaseShipBallScore(R.id.tnr);
            }
        });
        BtnMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mn = increaseShipBallScore(R.id.Mn);
            }
        });
        BtnMN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mn = decreaseShipBallScore(R.id.Mn);
            }
        });
        BtnMNr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmr = decreaseShipBallScore(R.id.nmr);
            }
        });
        BtnMPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nmr = increaseShipBallScore(R.id.nmr);
            }


        });
        BtnBP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bn = increaseShipBallScore(R.id.Bn);
            }
        });
        BtnBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bn = decreaseShipBallScore(R.id.Bn);
            }
        });
        BtnBNr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BNr = decreaseShipBallScore(R.id.BNr);
            }
        });

        BtnBPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BNr = increaseShipBallScore(R.id.BNr);

            }
        });
        TeamNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (!hasFocus) {
                   teamNumber = getIntValue(R.id.teamNumber, "Team Number");
                   if(teamNumber == 1706 ){
                      TeamImage.setImageResource(R.mipmap.rrlogo);
                   }else if(teamNumber == 4329){
                      TeamImage.setImageResource(R.mipmap.lr2);
                   }else{
                       TeamImage.setImageResource(R.mipmap.blank);
                   }
                }
            }
        });
        //Did Not Show Save Button
            Ns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = getDataDirectory();
                ScouterName = ((TextView) findViewById(R.id.ScouterName)).getText().toString();
                String notes = ((TextView) findViewById(R.id.notes)).getText().toString();
                teamNumber = getIntValue(R.id.teamNumber, "Team Number");
                int match = getIntValue(R.id.matchNumber, "Match Number");

                if (ScouterName == null || ScouterName.length() == 0 || match == 0
                        || teamNumber == 0
                        || ((Spinner)findViewById(R.id.wlt)).getSelectedItem().toString().equalsIgnoreCase("Results")
                        || ((Spinner)findViewById(R.id.StartingP)).getSelectedItem().toString().equalsIgnoreCase("Postion")
                        || ((Spinner)findViewById(R.id.EndGame)).getSelectedItem().toString().equalsIgnoreCase("EndGame")){
                    Toast.makeText(getApplicationContext(), "Please complete the form before saving", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast toast = Toast.makeText(getApplicationContext(), "Saving", Toast.LENGTH_SHORT);
                toast.show();
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
                    myOutWriter.println("RedAlliance: " + RedAlliance.isChecked());
                    myOutWriter.println("BlueAlliance: " + BlueAlliance.isChecked());
                    myOutWriter.println("Position: "+ ((Spinner)findViewById(R.id.StartingP)).getSelectedItem().toString());
                    myOutWriter.println("Balls in SandStorm: "+ ((Spinner)findViewById(R.id.sandBallA)).getSelectedItem().toString());
                    myOutWriter.println("Hatches in SandStorm: "+ ((Spinner)findViewById(R.id.sandHatchA)).getSelectedItem().toString());
                    myOutWriter.println("Fell Over: " + fo.isChecked());
                    myOutWriter.println("Broke Down: " + bd.isChecked());
                    myOutWriter.println("Communication issues: " + ci.isChecked());
                    myOutWriter.println("Dropped Battery/Bumpers/Parts: " + dbbp.isChecked());
                    myOutWriter.println("speed: " + ((Spinner) findViewById(R.id.speed)).getSelectedItem().toString());
                   // Left Rocket bleow
                    myOutWriter.println("LR_TLH: " + RTopleft.isChecked());
                    myOutWriter.println("LR_TRH: " + RTopright.isChecked());
                    myOutWriter.println("LR_MLH: " + RmiddleLeft.isChecked());
                    myOutWriter.println("LR_MRH: " + RmiddleRight.isChecked());
                    myOutWriter.println("LR_BLH: " + RBottoml.isChecked());
                    myOutWriter.println("LR_BRH: " + RBottomRight.isChecked());
                    // Right Rocket below
                    myOutWriter.println("RR_TLH: " + RTopleft1.isChecked());
                    myOutWriter.println("RR_TRH: " + RTopright1.isChecked());
                    myOutWriter.println("RR_MLH: " + RmiddleLeft1.isChecked());
                    myOutWriter.println("RR_MRH: " + RmiddleRight1.isChecked());
                   myOutWriter.println("RR_BLH: " + RBottomleft1.isChecked());
                    myOutWriter.println("RR_BRH: " + RBottomRight1.isChecked());

                    // Cargo Ship below
                    myOutWriter.println("Balls In top of Rocket: " + (tn + tnr));
                    myOutWriter.println("Balls In Middle of Rocket: " + (mn + nmr));
                    myOutWriter.println("Balls In Bottom of Rocket: " + (Bn + BNr));
                    // Cargo Ship Hatches
                    myOutWriter.println("CS_LH1: " + LH.isChecked() );
                    myOutWriter.println("CS_LH2: " + CLH.isChecked());
                    myOutWriter.println("CS_LH3: " + RLH.isChecked());
                    myOutWriter.println("CS_RH1: " + LRH.isChecked());
                    myOutWriter.println("CS_RH2: " + CRH.isChecked());
                    myOutWriter.println("CS_RH3: " + RRH.isChecked());
                    myOutWriter.println("CS_LH0: " + FLH.isChecked());
                    myOutWriter.println("CS_RH0: " + FRH.isChecked());
                    // Cargo Ship Balls
                    myOutWriter.println("CS_LB1: " + LB.isChecked() );
                    myOutWriter.println("CS_LB2: " + CLB.isChecked());
                    myOutWriter.println("CS_LB3: " + RLB.isChecked());
                    myOutWriter.println("CS_RB1: " + RLB.isChecked());
                    myOutWriter.println("CS_RB2: " + CRB.isChecked());
                    myOutWriter.println("CS_RB3: " + RRB.isChecked());
                    myOutWriter.println("CS_LB0: " + FLB.isChecked());
                    myOutWriter.println("CS_RB0: " + FRB.isChecked());

                    myOutWriter.println("notes: "+ notes);
                    myOutWriter.println("Won: "+ ((Spinner)findViewById(R.id.wlt)).getSelectedItem().toString());
                    myOutWriter.println("Level: "+ ((Spinner)findViewById(R.id.EndGame)).getSelectedItem().toString());



                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();

                    toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
                    toast.show();
                    resetapp();

                } catch (IOException e) {
                    toast = Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT);
                    Log.e("Exception", "File write failed: " + e.toString());
                }
            }
        });

               //Save Button
            S1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File dir = getDataDirectory();
                ScouterName = ((TextView) findViewById(R.id.ScouterName)).getText().toString();
                String notes = ((TextView) findViewById(R.id.notes)).getText().toString();
                teamNumber = getIntValue(R.id.teamNumber, "Team Number");
                int match = getIntValue(R.id.matchNumber, "Match Number");


                Toast toast = Toast.makeText(getApplicationContext(), "Saving", Toast.LENGTH_SHORT);
                toast.show();
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
                    myOutWriter.println("RedAlliance: " + RedAlliance.isChecked());
                    myOutWriter.println("BlueAlliance: " + BlueAlliance.isChecked());
                    myOutWriter.println("Postion: "+ "FALSE");
                    myOutWriter.println("Balls in SandStorm: "+ ((Spinner)findViewById(R.id.sandBallA)).getSelectedItem().toString());
                    myOutWriter.println("Hatches in SandStorm: "+ ((Spinner)findViewById(R.id.sandHatchA)).getSelectedItem().toString());
                    myOutWriter.println("Fell Over: " + fo.isChecked());
                    myOutWriter.println("Broke Down: " + bd.isChecked());
                    myOutWriter.println("Communication issues: " + ci.isChecked());
                    myOutWriter.println("Dropped Battery/Bumpers/Parts: " + dbbp.isChecked());
                    myOutWriter.println("speed: " + ((Spinner) findViewById(R.id.speed)).getSelectedItem().toString());
                   // Left Rocket bleow
                    myOutWriter.println("LR_TLH: " + RTopleft.isChecked());
                    myOutWriter.println("LR_TRH: " + RTopright.isChecked());
                    myOutWriter.println("LR_MLH: " + RmiddleLeft.isChecked());
                    myOutWriter.println("LR_MRH: " + RmiddleRight.isChecked());
                    myOutWriter.println("LR_BLH: " + RBottoml.isChecked());
                    myOutWriter.println("LR_BRH: " + RBottomRight.isChecked());
                    // Right Rocket below
                    myOutWriter.println("RR_TLH: " + RTopleft1.isChecked());
                    myOutWriter.println("RR_TRH: " + RTopright1.isChecked());
                    myOutWriter.println("RR_MLH: " + RmiddleLeft1.isChecked());
                    myOutWriter.println("RR_MRH: " + RmiddleRight1.isChecked());
                    myOutWriter.println("RR_BLH: " + RBottomleft1.isChecked());
                    myOutWriter.println("RR_BRH: " + RBottomRight1.isChecked());

                    // Cargo Ship below
                    myOutWriter.println("Balls In top of Rocket: " + (tn + tnr));
                    myOutWriter.println("Balls In Middle of Rocket: " + (mn + nmr));
                    myOutWriter.println("Balls In Bottom of Rocket: " + (Bn + BNr));
                    // Cargo Ship Hatches
                    myOutWriter.println("CS_LH1: " + LH.isChecked() );
                    myOutWriter.println("CS_LH2: " + CLH.isChecked());
                    myOutWriter.println("CS_LH3: " + RLH.isChecked());
                    myOutWriter.println("CS_RH1: " + LRH.isChecked());
                    myOutWriter.println("CS_RH2: " + CRH.isChecked());
                    myOutWriter.println("CS_RH3: " + RRH.isChecked());
                    myOutWriter.println("CS_LH0: " + FLH.isChecked());
                    myOutWriter.println("CS_RH0: " + FRH.isChecked());
                    // Cargo Ship Balls
                    myOutWriter.println("CS_LB1: " + LB.isChecked() );
                    myOutWriter.println("CS_LB2: " + CLB.isChecked());
                    myOutWriter.println("CS_LB3: " + RLB.isChecked());
                    myOutWriter.println("CS_RB1: " + RLB.isChecked());
                    myOutWriter.println("CS_RB2: " + CRB.isChecked());
                    myOutWriter.println("CS_RB3: " + RRB.isChecked());
                    myOutWriter.println("CS_LB0: " + FLB.isChecked());
                    myOutWriter.println("CS_RB0: " + FRB.isChecked());

                    myOutWriter.println("notes: "+ notes);
                    myOutWriter.println("Won: "+ "FALSE");
                    myOutWriter.println("Level: "+ "FALSE");



                    myOutWriter.flush();
                    myOutWriter.close();
                    fOut.close();

                    toast = Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT);
                    toast.show();
                    resetapp();

                } catch (IOException e) {
                    toast = Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT);
                    Log.e("Exception", "File write failed: " + e.toString());
                }
            }
        });
    }

    public void resetapp() {
        ((TextView) findViewById(R.id.matchNumber)).setText("" + (1 + Integer.parseInt(((TextView) findViewById(R.id.matchNumber)).getText().toString())));
        ((TextView) findViewById(R.id.teamNumber)).setText("");
        ((TextView) findViewById(R.id.notes)).setText("");
        ((CheckBox) findViewById(R.id.RTopleft)).setChecked(false);
        ((CheckBox) findViewById(R.id.RmiddleLeft)).setChecked(false);
        ((CheckBox) findViewById(R.id.RBottoml)).setChecked(false);
        ((CheckBox) findViewById(R.id.RTopright)).setChecked(false);
        ((CheckBox) findViewById(R.id.RmiddleRight)).setChecked(false);
        ((CheckBox) findViewById(R.id.RBottomRight)).setChecked(false);
        ((CheckBox) findViewById(R.id.RTopleft1)).setChecked(false);
        ((CheckBox) findViewById(R.id.Rmiddleleft1)).setChecked(false);
        ((CheckBox) findViewById(R.id.RBottomleft1)).setChecked(false);
        ((CheckBox) findViewById(R.id.RTopright1)).setChecked(false);
        ((CheckBox) findViewById(R.id.RmiddleRight1)).setChecked(false);
        ((CheckBox) findViewById(R.id.RBottomRight1)).setChecked(false);
        ((CheckBox) findViewById(R.id.CLB)).setChecked(false);
        ((CheckBox) findViewById(R.id.CLH)).setChecked(false);
        ((CheckBox) findViewById(R.id.LB)).setChecked(false);
        ((CheckBox) findViewById(R.id.LH)).setChecked(false);
        ((CheckBox) findViewById(R.id.RLB)).setChecked(false);
        ((CheckBox) findViewById(R.id.RLH)).setChecked(false);
        ((CheckBox) findViewById(R.id.FLB)).setChecked(false);
        ((CheckBox) findViewById(R.id.FLH)).setChecked(false);
        ((CheckBox) findViewById(R.id.LH)).setChecked(false);
        ((CheckBox) findViewById(R.id.dbbp)).setChecked(false);
        ((CheckBox) findViewById(R.id.ci)).setChecked(false);
        ((CheckBox) findViewById(R.id.bd)).setChecked(false);
        ((CheckBox) findViewById(R.id.fo)).setChecked(false);
        ((TextView) findViewById(R.id.tn)).setText("0");
        ((TextView) findViewById(R.id.Mn)).setText("0");
        ((TextView) findViewById(R.id.Bn)).setText("0");
        ((TextView) findViewById(R.id.tnr)).setText("0");
        ((TextView) findViewById(R.id.nmr)).setText("0");
        ((TextView) findViewById(R.id.BNr)).setText("0");

        ((CheckBox) findViewById(R.id.FRH)).setChecked(false);
        ((CheckBox) findViewById(R.id.FRB)).setChecked(false);
        ((CheckBox) findViewById(R.id.RRB)).setChecked(false);
        ((CheckBox) findViewById(R.id.RRH)).setChecked(false);
        ((CheckBox) findViewById(R.id.CRB)).setChecked(false);
        ((CheckBox) findViewById(R.id.CRH)).setChecked(false);
        ((CheckBox) findViewById(R.id.LRB)).setChecked(false);
        ((CheckBox) findViewById(R.id.LRH)).setChecked(false);
        ((Spinner) findViewById(R.id.wlt)).setSelection(0);
        ((Spinner) findViewById(R.id.EndGame)).setSelection(0);
        ((Spinner) findViewById(R.id.StartingP)).setSelection(0);
        ((Spinner) findViewById(R.id.sandBallA)).setSelection(0);
        ((Spinner) findViewById(R.id.sandHatchA)).setSelection(0);
        ((Spinner) findViewById(R.id.speed)).setSelection(0);

        SandButABackround = false;
        SandButVBackround = false;

        inTeleop = false;
        sandstormscore = 0;
        TeamImage.setImageResource(R.mipmap.blank);
    }

    private int getIntValue(int id, String name) {
        String textValue = "" + ((TextView) findViewById(id)).getText();
        try {
            return Integer.parseInt(textValue);
        } catch (NumberFormatException n) {
            Toast.makeText(getApplicationContext(), name + " must have a numeric value", Toast.LENGTH_SHORT).show();
            return 0;
        }

    }
private File getDataDirectory() {    File directory = Environment.getExternalStorageDirectory();
        File myDir = new File(directory + "/ScoutingData");
        myDir.mkdirs();
        return myDir;
    }
}
