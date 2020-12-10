package hcmus.nhom21.parcheesigame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

    private ImageView imgHorse0;
    private ImageView imgHorse1;
    private ImageView imgHorse2;
    private ImageView imgHorse3;

    private Button btnExit;
    private Button btnPlayAgain;
    private TextView txtTeamName;
    SharedPreferences sharedPreferences;
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winninggame);

        sharedPreferences = getSharedPreferences("dataLoadGame", MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putBoolean("hasLoadGame",false);
        editor.apply();
        imgFireworkAnim0 = (ImageView) findViewById(R.id.imgFireworkAnim0);
        imgFireworkAnim1 = (ImageView) findViewById(R.id.imgFireworkAnim1);
        imgFireworkAnim2 = (ImageView) findViewById(R.id.imgFireworkAnim2);
        imgHorse0 = (ImageView) findViewById(R.id.imgHorse0);
        imgHorse1 = (ImageView) findViewById(R.id.imgHorse1);
        imgHorse2 = (ImageView) findViewById(R.id.imgHorse2);
        imgHorse3 = (ImageView) findViewById(R.id.imgHorse3);
        btnExit = (Button) findViewById(R.id.btnExit);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);
        txtTeamName = (TextView) findViewById(R.id.txtTeamName);

        Intent runningGameIntent = getIntent();
        String strTeamName = runningGameIntent.getStringExtra("teamName");


        switch (strTeamName){
            case "Re":
                txtTeamName.setText(R.string.redTeam);
                txtTeamName.setTextColor(R.color.colorRed);
                imgHorse0.setImageResource(R.drawable.red_winhorse);
                imgHorse1.setImageResource(R.drawable.red_winhorse);
                imgHorse2.setImageResource(R.drawable.red_winhorse);
                imgHorse3.setImageResource(R.drawable.red_winhorse);
                break;
            case "Yellow":
                txtTeamName.setText(R.string.yellowTeam);
                txtTeamName.setTextColor(R.color.colorYellow);
                imgHorse0.setImageResource(R.drawable.yellow_winhorse);
                imgHorse1.setImageResource(R.drawable.yellow_winhorse);
                imgHorse2.setImageResource(R.drawable.yellow_winhorse);
                imgHorse3.setImageResource(R.drawable.yellow_winhorse);
                break;
            case "Blue":
                txtTeamName.setText(R.string.blueTeam);
                txtTeamName.setTextColor(R.color.colorBlue);
                imgHorse0.setImageResource(R.drawable.blue_winhorse);
                imgHorse1.setImageResource(R.drawable.blue_winhorse);
                imgHorse2.setImageResource(R.drawable.blue_winhorse);
                imgHorse3.setImageResource(R.drawable.blue_winhorse);
                break;
            case "Green":
                txtTeamName.setText(R.string.greenTeam);
                txtTeamName.setTextColor(R.color.colorGreen);
                imgHorse0.setImageResource(R.drawable.green_winhorse);
                imgHorse1.setImageResource(R.drawable.green_winhorse);
                imgHorse2.setImageResource(R.drawable.green_winhorse);
                imgHorse3.setImageResource(R.drawable.green_winhorse);
                break;
            default: break;
        }

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
                Intent intentSetingGame = new Intent(WinGameActivity.this,SetUpNewGameActivity.class);
                startActivity(intentSetingGame);
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

    @Override
    public void onBackPressed() { }
}
