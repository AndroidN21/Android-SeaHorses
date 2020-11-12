package hcmus.nhom21.demoparchessi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RunningGameActivity extends Activity {
    private Button btnTypePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runninggame);
        Intent intentMain = getIntent();

        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);

        btnTypePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_android_24);
            }
        });
    }
}
