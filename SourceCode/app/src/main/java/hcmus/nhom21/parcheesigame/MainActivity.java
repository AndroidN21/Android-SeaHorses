package hcmus.nhom21.parcheesigame;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends FragmentActivity {
    private Button btnNewGame;
    private Button btnInfo;
    private Button btnSetting;
    private Button btnRule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Intent intentMain = getIntent();

        btnNewGame = (Button) findViewById(R.id.btnNewGame);
        btnRule = (Button) findViewById(R.id.btnRule);
        btnInfo = (Button) findViewById(R.id.btnInfo);
        btnSetting = (Button) findViewById(R.id.btnSetting);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSetUpNewGame = new Intent();
                intentSetUpNewGame.setClass(v.getContext(), RunningGameActivity.class);
                startActivity(intentSetUpNewGame);
            }
        });

        btnRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRule = new Intent();
                intentRule.setClass(v.getContext(), RuleActivity.class);
                startActivity(intentRule);
            }
        });

        btnInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent();
                infoIntent.setClass(v.getContext(), CopyRightInfoActivity.class);
                startActivity(infoIntent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent infoIntent = new Intent();
                infoIntent.setClass(v.getContext(), SettingMenuActivity.class);
                startActivity(infoIntent);
            }
        });
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            finishAffinity();
//            finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }
}
