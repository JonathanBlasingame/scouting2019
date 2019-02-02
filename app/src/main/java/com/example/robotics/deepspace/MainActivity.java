package com.example.robotics.deepspace;

import android.app.ActionBar;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ImageSwitcher;
import android.widget.ViewSwitcher;
import android.graphics.Color;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageSwitcher sw;
    private ImageSwitcher sw1;
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
    Button svision;
    Button sauto;
    boolean inTeleop;
    boolean color = false;
    boolean inTeleopColor;
    ImageButton S1;
    ImageView Timage;

    TextView sandBallA;
    TextView sandHatchA;

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
    CheckBox LRB;
    CheckBox CRB;
    CheckBox RRB;
    CheckBox FRB;
    CheckBox Teleop_in;


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
    TextView txtCount;

    public void setSandstormScore(int score) {
        sandBallA.setText("" + score);
    }

    public void setHatchScore(int score) {
        sandHatchA.setText("" + score);
    }

    int increaseShipBallScore(int id) {
        TextView txtCount = findViewById(id);
        String countValue = txtCount.getText().toString();
        int intCountValue = Integer.parseInt(countValue);
        int oldValue = intCountValue;
        intCountValue = Math.min(intCountValue - 0, 1);
        intCountValue++;
        txtCount.setText(String.valueOf(intCountValue));
        if (oldValue != intCountValue) {
            if (inTeleop) {
                // we are in telleop
                teleopscore += (intCountValue - oldValue);
            } else {
                // we are in Sandstorm
                sandstormscore += (intCountValue - oldValue);
                setSandstormScore(sandstormscore);
            }
        }
        return intCountValue;
    }

    int decreaseShipBallScore(int id) {
        TextView txtCount = findViewById(id);
        String countValue = txtCount.getText().toString();
        int intCountValue = Integer.parseInt(countValue);
        int oldValue = intCountValue;
        intCountValue = Math.max(intCountValue - 1, 0);
        txtCount.setText(String.valueOf(intCountValue));
        if (oldValue != intCountValue) {
            if (inTeleop) {
                teleopscore += (intCountValue - oldValue);
            } else {
                sandstormscore += (intCountValue - oldValue);
                setSandstormScore(sandstormscore);
            }
        }
        return intCountValue;
    }

    void handleCheckboxChange(boolean isChecked) {
        if (inTeleop) {
            teleopscore += isChecked ? 1 : -1;
        } else {
            hatchScore += isChecked ? 1 : -1;
            setHatchScore(hatchScore);
        }
    }

    void handleBallCheckboxChange(boolean isChecked) {
        if (inTeleop) {
            teleopscore += isChecked ? 1 : -1;
        } else {
            sandstormscore += isChecked ? 1 : -1;
            setSandstormScore(sandstormscore);
        }
    }

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
        StandStorm = findViewById(R.id.StandStorm);
        Teleop = findViewById(R.id.Teleop);
        Teleop = findViewById(R.id.Teleop);
        sandBallA = findViewById(R.id.sandBallA);
        sandHatchA = findViewById(R.id.sandHatchA);
        RTopleft = findViewById(R.id.RTopleft);
        RTopleft1 = findViewById(R.id.RTopleft1);
        RTopright = findViewById(R.id.RTopright);
        RTopright1 = findViewById(R.id.RTopright1);
        RmiddleRight = findViewById(R.id.RmiddleRight);
        RmiddleRight1 = findViewById(R.id.RmiddleRight1);
        RmiddleLeft1 = findViewById(R.id.Rmiddleleft1);
        RmiddleLeft = findViewById(R.id.RmiddleLeft);
        svision = findViewById(R.id.svision);
        sauto = findViewById(R.id.sauto);
        sw = (ImageSwitcher) findViewById(R.id.imageSwitcher1);
        sw1 = (ImageSwitcher) findViewById(R.id.imageSwitcher2);

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
        LRB = findViewById(R.id.LRB);
        FRB = findViewById(R.id.FRB);

        ((CheckBox)findViewById(R.id.blueAlliance)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    findViewById(R.id.main).setBackgroundColor(Color.rgb(100,149,237));
                }
            }
        });
        ((CheckBox)findViewById(R.id.RedAlliance)).setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    findViewById(R.id.main).setBackgroundColor(Color.rgb(220,20,60));
                }
            }
        });

        inTeleop = false;
        inTeleopColor = false;


        StandStorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });
        sw1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });
        sw1.setImageResource(R.mipmap.g400);
        Teleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                }
        });
    RTopleft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RTopleft1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RTopright.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RTopright1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RmiddleRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RmiddleRight1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RmiddleLeft1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RmiddleLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RBottoml.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RBottomRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RBottomleft1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RBottomRight1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        LH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        CLH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RLH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        FLH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        LRH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        CRH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        RRH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        FRH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleCheckboxChange(isChecked);
            }
        });
        LB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });
        CLB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });
        RLB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });
        FLB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });
        LRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });
        CRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });
        RRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });
        FRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleBallCheckboxChange(isChecked);
            }
        });



        StandStorm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inTeleop = false;
                sw1.setImageResource(R.mipmap.g383);
                sw.setImageResource(R.mipmap.blank);
            }
        });

        Teleop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inTeleop = true;
                sw.setImageResource(R.mipmap.g383);
                sw1.setImageResource(R.mipmap.blank);
            }
        });

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
        svision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                svision.setTextColor(Color.MAGENTA);
        }

    });
        sauto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sauto.setTextColor(Color.MAGENTA);
            }

        });

        BtnBPr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BNr = increaseShipBallScore(R.id.BNr);

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

    public void resetapp() {
        ((TextView) findViewById(R.id.matchNumber)).setText("" + (1 + Integer.parseInt(((TextView) findViewById(R.id.matchNumber)).getText().toString())));
        ((TextView) findViewById(R.id.teamNumber)).setText("");
        ((TextView) findViewById(R.id.finalScore)).setText("");
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
        ((Button)   findViewById(R.id.svision)).setTextColor(Color.BLACK);
        ((Button)   findViewById(R.id.sauto)).setTextColor(Color.BLACK);
        ((Spinner) findViewById(R.id.wlt)).setSelection(0);
        ((Spinner) findViewById(R.id.speed)).setSelection(0);
        sandstormscore = 0;
        setSandstormScore(0);
        sw1.setImageResource(R.mipmap.g383);
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
}




