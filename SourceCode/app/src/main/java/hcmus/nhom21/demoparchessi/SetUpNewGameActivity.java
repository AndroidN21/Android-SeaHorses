package hcmus.nhom21.demoparchessi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

public class SetUpNewGameActivity extends Activity  {
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
                if((countPlayer()>1||!radYPlayer.isChecked())&&countNone()<3){
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
                if((countPlayer()>1||!radBPlayer.isChecked())&&countNone()<3){
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
                if((countPlayer()>1||!radGPlayer.isChecked())&&countNone()<3){
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
                if((countPlayer()>1||!radRPlayer.isChecked())&&countNone()<3){
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
                String setupPlayer="";
                 if(radYPlayer.isChecked())setupPlayer+="Player";
                 if(radYBoot.isChecked())setupPlayer+="Boot";
                 if(radYNone.isChecked())setupPlayer+="None";
                 if(radBPlayer.isChecked())setupPlayer+="#Player";
                 if(radBBoot.isChecked())setupPlayer+="#Boot";
                 if(radBNone.isChecked())setupPlayer+="#None";
                 if(radGPlayer.isChecked())setupPlayer+="#Player";
                 if(radGBoot.isChecked())setupPlayer+="#Boot";
                 if(radGNone.isChecked())setupPlayer+="#None";
                 if(radRPlayer.isChecked())setupPlayer+="#Player";
                 if(radRBoot.isChecked())setupPlayer+="#Boot";
                 if(radRNone.isChecked())setupPlayer+="#None";
                 Intent intentRunningGame = new Intent();
                 intentRunningGame.setClass(v.getContext(), RunningGameActivity.class);
                 intentRunningGame.putExtra("setupPlayer",setupPlayer);
                 startActivity(intentRunningGame);
                 finish();
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
