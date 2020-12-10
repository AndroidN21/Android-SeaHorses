package hcmus.nhom21.parcheesigame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class LoseGameActivity extends Activity {
    private Button btnExit;
    private Button btnPlayAgain;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lossinggame);

        btnExit = (Button) findViewById(R.id.btnExit);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        Intent intentLosingGame = getIntent();

//        switch (strTeamName){
//            case "redTeam":

//                break;
//            case "yellowTeam":

//                break;
//            case "blueTeam":

//                break;
//            case "greenTeam":

//            default:

//        }



        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSetingGame = new Intent(LoseGameActivity.this,SetUpNewGameActivity.class);
                startActivity(intentSetingGame);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onBackPressed() { }

}
