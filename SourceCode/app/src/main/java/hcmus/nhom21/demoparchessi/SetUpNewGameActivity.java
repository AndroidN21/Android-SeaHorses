package hcmus.nhom21.demoparchessi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class SetUpNewGameActivity extends Activity {
    private Button btnStart;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_newgame);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRunningGame = new Intent();
                intentRunningGame.setClass(v.getContext(), RunningGameActivity.class);
                startActivity(intentRunningGame);
            }
        });
    }
}
