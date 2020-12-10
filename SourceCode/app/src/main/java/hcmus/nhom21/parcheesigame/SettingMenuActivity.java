package hcmus.nhom21.parcheesigame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SeekBar;
import androidx.annotation.Nullable;


public class SettingMenuActivity extends Activity {
    SeekBar volumeBar;
    SeekBar sfxBar;
    CheckBox btnVibrate;
    ImageView imgVolumeUp;
    ImageView imgVolumeDown;
    ImageView imgSFXUp;
    ImageView imgSFXDown;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        volumeBar = findViewById(R.id.volumeBar);
        sfxBar = findViewById(R.id.sfxBar);
        btnVibrate = findViewById(R.id.btnVibrate);

        imgVolumeUp =  findViewById(R.id.imgVolumeUp);
        imgVolumeDown =  findViewById(R.id.imgVolumeDown);
        imgSFXUp =  findViewById(R.id.imgSFXup);
        imgSFXDown =  findViewById(R.id.imgSFXdown);

        volumeBar.setProgress(MusicThread.GetVolume());
        sfxBar.setProgress(SFXThread.GetVolume());

        if (!VibrateThread.State()){
            btnVibrate.setChecked(true);
        }

        volumeBar.setProgress(MusicThread.GetVolume());
        sfxBar.setProgress(SFXThread.GetVolume());

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MusicThread.SetVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no no
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no no
            }
        });

        sfxBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SFXThread.SetVolume(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // no no
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // no no
            }
        });

        imgVolumeDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                MusicThread.SetVolume(0);
                volumeBar.setProgress(0);
            }
        });

        imgVolumeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                MusicThread.SetVolume(100);
                volumeBar.setProgress(100);
            }
        });

        imgSFXDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                SFXThread.SetVolume(0);
                sfxBar.setProgress(0);
            }
        });

        imgSFXUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                SFXThread.SetVolume(100);
                sfxBar.setProgress(100);
            }
        });

        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                if (btnVibrate.isChecked()){
                    VibrateThread.Off();
                }else {
                    VibrateThread.On();
                }
            }
        });
    }
}
