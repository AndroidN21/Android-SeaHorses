package hcmus.nhom21.demoparchessi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class WinGameActivity extends Activity {
    private AnimationDrawable fireworkAnim0;
    private AnimationDrawable fireworkAnim1;
    private AnimationDrawable fireworkAnim2;
    private ImageView imgFireworkAnim0;
    private ImageView imgFireworkAnim1;
    private ImageView imgFireworkAnim2;
    private Button btnExit;
    private Button btnPlayAgain;
    private TextView txtTeamName;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winninggame);

        imgFireworkAnim0 = (ImageView) findViewById(R.id.imgFireworkAnim0);
        imgFireworkAnim1 = (ImageView) findViewById(R.id.imgFireworkAnim1);
        imgFireworkAnim2 = (ImageView) findViewById(R.id.imgFireworkAnim2);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        txtTeamName = (TextView) findViewById(R.id.txtTeamName);

        Intent runningGameIntent = getIntent();
        String strTeamName = runningGameIntent.getStringExtra("teamName");

//        switch (strTeamName){
//            case "redTeam":
//                txtTeamName.setText(R.string.redTeam);
//                txtTeamName.setTextColor(R.color.colorRed);
//                break;
//            case "yellowTeam":
//                txtTeamName.setText(R.string.yellowTeam);
//                txtTeamName.setTextColor(R.color.colorYellow);
//                break;
//            case "blueTeam":
//                txtTeamName.setText(R.string.blueTeam);
//                txtTeamName.setTextColor(R.color.colorBlue);
//                break;
//            case "greenTeam":
//                txtTeamName.setText(R.string.greenTeam);
//                txtTeamName.setTextColor(R.color.colorGreen);
//            default:
//        }

        //Hiệu ứng pháo hoa
        imgFireworkAnim0.setBackgroundResource(R.drawable.animation_firework_type0);
        fireworkAnim0 = (AnimationDrawable) imgFireworkAnim0.getBackground();

        imgFireworkAnim1.setBackgroundResource(R.drawable.animation_firework_type0);
        fireworkAnim1 = (AnimationDrawable) imgFireworkAnim1.getBackground();

        imgFireworkAnim2.setBackgroundResource(R.drawable.animation_firework_type0);
        fireworkAnim2 = (AnimationDrawable) imgFireworkAnim2.getBackground();


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
        fireworkAnim0.start();
        fireworkAnim1.start();
        fireworkAnim2.start();
    }


}
