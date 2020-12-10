package hcmus.nhom21.parcheesigame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import hcmus.nhom21.handle.ChessBoard;
import hcmus.nhom21.handle.User;

public class SetUpNewGameActivity extends Activity {
    private Button btnStart;
    private RadioButton radYPlayer;
    private RadioButton radYBoot;
    private RadioButton radYNone;
    private RadioButton radBPlayer;
    private RadioButton radBBoot;
    private RadioButton radBNone;
    private RadioButton radGPlayer;
    private RadioButton radGBoot;
    private RadioButton radGNone;
    private RadioButton radRPlayer;
    private RadioButton radRBoot;
    private RadioButton radRNone;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_newgame);
        radYPlayer = findViewById(R.id.rad_yellow_person);
        radYBoot = findViewById(R.id.rad_yellow_android);
        radYNone = findViewById(R.id.rad_yellow_none);
        radBPlayer = findViewById(R.id.rad_blue_person);
        radBBoot = findViewById(R.id.rad_blue_android);
        radBNone = findViewById(R.id.rad_blue_none);
        radGPlayer = findViewById(R.id.rad_green_person);
        radGBoot = findViewById(R.id.rad_green_android);
        radGNone = findViewById(R.id.rad_green_none);
        radRPlayer = findViewById(R.id.rad_red_person);
        radRBoot = findViewById(R.id.rad_red_android);
        radRNone = findViewById(R.id.rad_red_none);

        radYPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radYPlayer.setChecked(true);
                radYBoot.setChecked(false);
                radYNone.setChecked(false);
            }
        });
        radYBoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countPlayer()>1||!radYPlayer.isChecked()){
                    radYPlayer.setChecked(false);
                    radYBoot.setChecked(true);
                    radYNone.setChecked(false);
                }
                else radYBoot.setChecked(!radYBoot.isChecked());
            }
        });
        radYNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((countPlayer()>1||!radYPlayer.isChecked())&&countNone()<2){
                    radYPlayer.setChecked(false);
                    radYBoot.setChecked(false);
                    radYNone.setChecked(true);
                }
                else radYNone.setChecked(!radYNone.isChecked());
            }
        });
        radBPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radBPlayer.setChecked(true);
                radBBoot.setChecked(false);
                radBNone.setChecked(false);
            }
        });
        radBBoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countPlayer()>1||!radBPlayer.isChecked()){
                    radBPlayer.setChecked(false);
                    radBBoot.setChecked(true);
                    radBNone.setChecked(false);
                }
                else radBBoot.setChecked(!radBBoot.isChecked());
            }
        });
        radBNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((countPlayer()>1||!radBPlayer.isChecked())&&countNone()<2){
                    radBPlayer.setChecked(false);
                    radBBoot.setChecked(false);
                    radBNone.setChecked(true);
                }
                else radBNone.setChecked(!radBNone.isChecked());
            }
        });
        radGPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radGPlayer.setChecked(true);
                radGBoot.setChecked(false);
                radGNone.setChecked(false);
            }
        });
        radGBoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countPlayer()>1||!radGPlayer.isChecked()){
                    radGPlayer.setChecked(false);
                    radGBoot.setChecked(true);
                    radGNone.setChecked(false);
                }
                else radGBoot.setChecked(!radGBoot.isChecked());
            }
        });
        radGNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((countPlayer()>1||!radGPlayer.isChecked())&&countNone()<2){
                    radGPlayer.setChecked(false);
                    radGBoot.setChecked(false);
                    radGNone.setChecked(true);
                }
                else radGNone.setChecked(!radGNone.isChecked());
            }
        });
        radRPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radRPlayer.setChecked(true);
                radRBoot.setChecked(false);
                radRNone.setChecked(false);
            }
        });
        radRBoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countPlayer()>1||!radRPlayer.isChecked()){
                    radRPlayer.setChecked(false);
                    radRBoot.setChecked(true);
                    radRNone.setChecked(false);
                }
                else radRBoot.setChecked(!radRBoot.isChecked());
            }
        });
        radRNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((countPlayer()>1||!radRPlayer.isChecked())&&countNone()<2){
                    radRPlayer.setChecked(false);
                    radRBoot.setChecked(false);
                    radRNone.setChecked(true);
                }
                else radRNone.setChecked(!radRNone.isChecked());
            }
        });
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String setupPlayer = "";
                int[] arrSetupPlayer=new int[4];
//                if (radYPlayer.isChecked() == true) setupPlayer += "#radYPlayer";
//                if (radYBoot.isChecked() == true) setupPlayer += "#radYBoot";
//                if (radYNone.isChecked() == true) setupPlayer += "#radYNone";
//                if (radBPlayer.isChecked() == true) setupPlayer += "#radBPlayer";
//                if (radBBoot.isChecked() == true) setupPlayer += "#radBBoot";
//                if (radBNone.isChecked() == true) setupPlayer += "#radBNone";
//                if (radGPlayer.isChecked() == true) setupPlayer += "#radGPlayer";
//                if (radGBoot.isChecked() == true) setupPlayer += "#radGBoot";
//                if (radGNone.isChecked() == true) setupPlayer += "#radGNone";
//                if (radRPlayer.isChecked() == true) setupPlayer += "#radRPlayer";
//                if (radRBoot.isChecked() == true) setupPlayer += "#radRBoot";
//                if (radRNone.isChecked() == true) setupPlayer += "#radRNone";

                if (radYPlayer.isChecked() == true) arrSetupPlayer[0] = User.MODE_USER;
                if (radYBoot.isChecked() == true) arrSetupPlayer[0] = User.MODE_BOOT;
                if (radYNone.isChecked() == true) arrSetupPlayer[0] = User.MODE_NONE;
                if (radRPlayer.isChecked() == true) arrSetupPlayer[1] +=User.MODE_USER;
                if (radRBoot.isChecked() == true) arrSetupPlayer[1] += User.MODE_BOOT;
                if (radRNone.isChecked() == true) arrSetupPlayer[1] += User.MODE_NONE;
                if (radBPlayer.isChecked() == true) arrSetupPlayer[2] += User.MODE_USER;
                if (radBBoot.isChecked() == true) arrSetupPlayer[2] += User.MODE_BOOT;
                if (radBNone.isChecked() == true) arrSetupPlayer[2] += User.MODE_NONE;
                if (radGPlayer.isChecked() == true) arrSetupPlayer[3] += User.MODE_USER;
                if (radGBoot.isChecked() == true) arrSetupPlayer[3] += User.MODE_BOOT;
                if (radGNone.isChecked() == true) arrSetupPlayer[3] += User.MODE_NONE;

                //Log.e("mylog", setupPlayer);
                Intent intentRunningGame = new Intent();
                intentRunningGame.setClass(v.getContext(), RunningGameActivity.class);
                //intentRunningGame.putExtra("setupPlayer", setupPlayer);
                intentRunningGame.putExtra("arrSetupPlayer",arrSetupPlayer);
                startActivity(intentRunningGame);
            }
        });
    }

    private int countNone() {
        int count = 0;
        if(radBNone.isChecked())count++;
        if(radYNone.isChecked())count++;
        if(radGNone.isChecked())count++;
        if(radRNone.isChecked())count++;
        return count;
    }
    private int countPlayer(){
        int count = 0;
        if(radBPlayer.isChecked())count++;
        if(radYPlayer.isChecked())count++;
        if(radGPlayer.isChecked())count++;
        if(radRPlayer.isChecked())count++;
        return count;
    }

    private boolean checkTwoNone() {return  true;}
    private boolean checkOnePlayer() {return  true;}

}
