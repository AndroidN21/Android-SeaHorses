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
                radYPlayer.setChecked(false);
                radYBoot.setChecked(true);
                radYNone.setChecked(false);
            }
        });
        radYNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radYPlayer.setChecked(false);
                radYBoot.setChecked(false);
                radYNone.setChecked(true);
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
                radBPlayer.setChecked(false);
                radBBoot.setChecked(true);
                radBNone.setChecked(false);
            }
        });
        radBNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radBPlayer.setChecked(false);
                radBBoot.setChecked(false);
                radBNone.setChecked(true);
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
                radGPlayer.setChecked(false);
                radGBoot.setChecked(true);
                radGNone.setChecked(false);
            }
        });
        radGNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radGPlayer.setChecked(false);
                radGBoot.setChecked(false);
                radGNone.setChecked(true);
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
                radRPlayer.setChecked(false);
                radRBoot.setChecked(true);
                radRNone.setChecked(false);
            }
        });
        radRNone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radRPlayer.setChecked(false);
                radRBoot.setChecked(false);
                radRNone.setChecked(true);
            }
        });
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String setupPlayer="";
                 if(radYPlayer.isChecked())setupPlayer+="#radYPlayer";
                 if(radYBoot.isChecked())setupPlayer+="#radYBoot";
                 if(radYNone.isChecked())setupPlayer+="#radYNone";
                 if(radBPlayer.isChecked())setupPlayer+="#radBPlayer";
                 if(radBBoot.isChecked())setupPlayer+="#radBBoot";
                 if(radBNone.isChecked())setupPlayer+="#radBNone";
                 if(radGPlayer.isChecked())setupPlayer+="#radGPlayer";
                 if(radGBoot.isChecked())setupPlayer+="#radGBoot";
                 if(radGNone.isChecked())setupPlayer+="#radGNone";
                 if(radRPlayer.isChecked())setupPlayer+="#radRPlayer";
                 if(radRBoot.isChecked())setupPlayer+="#radRBoot";
                 if(radRNone.isChecked())setupPlayer+="#radRNone";
                Intent intentRunningGame = new Intent();
                intentRunningGame.setClass(v.getContext(), RunningGameActivity.class);
                intentRunningGame.putExtra("setupPlayer",setupPlayer);
                startActivity(intentRunningGame);
            }
        });
    }
}
