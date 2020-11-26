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
    private AnimationDrawable flagAnim0;
    private AnimationDrawable flagAnim1;
    private AnimationDrawable flagAnim2;
    private ImageView imgFlagAnim0;
    private ImageView imgFlagAnim1;
    private ImageView imgFlagAnim2;

    private Button btnExit;
    private Button btnPlayAgain;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lossinggame);

        //imgFlagAnim0 = (ImageView) findViewById(R.id.imgFlagAnim0);
        //imgFlagAnim1 = (ImageView) findViewById(R.id.imgFlagAnim1);
        //imgFlagAnim2 = (ImageView) findViewById(R.id.imgFlagAnim2);

        btnExit = (Button) findViewById(R.id.btnExit);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        Intent runningGameIntent = getIntent();

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

        //Hiệu ứng cờ bay
        //imgFlagAnim0.setBackgroundResource(R.drawable.animation_Flag_type0);
        //FlagAnim0 = (AnimationDrawable) imgFlagAnim0.getBackground();

        //imgFlagAnim1.setBackgroundResource(R.drawable.animation_Flag_type0);
        //FlagAnim1 = (AnimationDrawable) imgFlagAnim1.getBackground();

        //imgFlagAnim2.setBackgroundResource(R.drawable.animation_Flag_type0);
        //FlagAnim2 = (AnimationDrawable) imgFlagAnim2.getBackground();

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //FlagAnim0.start();
        //FlagAnim1.start();
        //FlagAnim2.start();
    }


}
