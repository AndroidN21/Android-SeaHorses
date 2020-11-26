package hcmus.nhom21.parcheesigame;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class SettingMenuActivity extends FragmentActivity {
    SeekBar volumeBar;
    Button btnSFX;
    Button btnVibrate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_setting);

        volumeBar = findViewById(R.id.volumeBar);
        btnSFX = (Button) findViewById(R.id.btnSFX);
        btnVibrate = (Button) findViewById(R.id.btnVibrate);

        volumeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // no no
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



        btnSFX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        btnVibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
