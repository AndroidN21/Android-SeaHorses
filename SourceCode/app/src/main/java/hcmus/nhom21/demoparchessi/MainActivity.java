package hcmus.nhom21.demoparchessi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
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

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSetUpNewGame = new Intent();
                intentSetUpNewGame.setClass(v.getContext(), SetUpNewGameActivity.class);
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
    }

}