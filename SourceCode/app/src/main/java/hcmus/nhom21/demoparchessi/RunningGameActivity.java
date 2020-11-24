package hcmus.nhom21.demoparchessi;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

public class RunningGameActivity extends FragmentActivity  {
    private Button btnTypePlayer;
    private ImageButton btnSetting;
    private Image imgBoard;
    private Button btnProfile;
    boolean flagTypePlayer = false;
    boolean flagHide = false;

    FragmentTransaction ft;

    //-------------------------------------1712275-----------------------------------
    FragmentDice fragYDice = new FragmentDice();
    FragmentDice fragRDice = new FragmentDice();
    FragmentDice fragBDice = new FragmentDice();
    FragmentDice fragGDice = new FragmentDice();

    private int turn = 1;
    //1 yellow
    //2 blue
    //3 green
    //4 red

    //public ArrayList<User> listUser;       // gỡ cmt khi merge

    static private int speed = 2000;
    Handler myHandler = new Handler(Looper.getMainLooper());
    private int resRollDiceOne = 3;
    private int resRollDiceTwo = 6;

    private Button btnRoll;

    private LinearLayout viewProfile;

    public void setResRollDiceOne(int resRollDiceOne) {
        this.resRollDiceOne = resRollDiceOne;
    }

    public void setResRollDiceTwo(int resRollDiceTwo) {
        this.resRollDiceTwo = resRollDiceTwo;
    }

    public int getResRollDiceOne() {
        return resRollDiceOne;
    }

    public int getResRollDiceTwo() {
        return resRollDiceTwo;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;

//        if(listUser[turn].isPlayer) btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_person_24);
//        else btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_android_24);
        // gỡ cmt sau khi merge

        switch (turn){
            case 1: viewProfile.setBackgroundResource(R.drawable.ic_profile_yellow);break;
            case 2: viewProfile.setBackgroundResource(R.drawable.ic_profile_blue);break;
            case 3: viewProfile.setBackgroundResource(R.drawable.ic_profile_green);break;
            case 4: viewProfile.setBackgroundResource(R.drawable.ic_profile_red);break;
        }
    }

    //-------------------------------------1712275-----------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runninggame);
        //Intent intentMain = getIntent();

        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);
        btnSetting = (ImageButton) findViewById(R.id.btnSetting);

        //Dùng View Holder
        //ViewHolder viewHolder = new ViewHolder();

        //.setTag(viewHolder);

        //Bật tắt chế độ auto
        btnTypePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagTypePlayer){
                    flagTypePlayer = true;
                    //btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_android_24);
                    Toast.makeText(RunningGameActivity.this, "Bật tự động chơi", Toast.LENGTH_SHORT).show();
                }
                else{
                    flagTypePlayer = false;
                    //btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_person_24);
                    Toast.makeText(RunningGameActivity.this, "Tắt tự động chơi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Mở menu cài đặt
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ft = getSupportFragmentManager().beginTransaction();
                btnSetting.setVisibility(View.INVISIBLE);

                SettingFragment settingFragment = new SettingFragment();
                ft.replace(R.id.frameSetting, settingFragment);
                ft.addToBackStack(null);
                ft.commit();

                //Ẩn/Vô hiệu hóa/Chèn fragment lên trên cùng của activiy hiện tại
                findViewById(R.id.imgBoard).setVisibility(View.INVISIBLE);
                findViewById(R.id.txtProfile).setVisibility(View.INVISIBLE);
                flagHide = true;
            }
        });
        //-------------------------------------1712275-----------------------------------
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frg_dice_yellow, fragYDice);
        ft.replace(R.id.frg_dice_blue, fragBDice);
        ft.replace(R.id.frg_dice_green, fragGDice);
        ft.replace(R.id.frg_dice_red, fragRDice);
        ft.addToBackStack(null);
        ft.commit();
        btnRoll = findViewById(R.id.btn_roll); //btn để test
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random generator = new Random();
                setTurn(generator.nextInt(4)+1);
                rollDice(getTurn());
            }
        });

        viewProfile = findViewById(R.id.txtProfile);
        //-------------------------------------1712275-----------------------------------
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            //Toast.makeText(RunningGameActivity.this, "Kill running", Toast.LENGTH_SHORT).show();
            if (flagHide){
                flagHide = false;
                findViewById(R.id.imgBoard).setVisibility(View.VISIBLE);
                findViewById(R.id.txtProfile).setVisibility(View.VISIBLE);
                findViewById(R.id.btnSetting).setVisibility(View.VISIBLE);
            }
            //finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    static class ViewHolder{
        Button btnTypePlayer;
        ImageButton btnSetting;
        Image imgBoard;
        Button btnProfile;
    }
    //--------------------------------------------------------------------------1712275

    private void rollDice(int turn){
        switch (turn){
            case 1: findViewById(R.id.frg_dice_yellow).setVisibility(View.VISIBLE);break;
            case 2: findViewById(R.id.frg_dice_blue).setVisibility(View.VISIBLE);break;
            case 3: findViewById(R.id.frg_dice_green).setVisibility(View.VISIBLE);break;
            case 4: findViewById(R.id.frg_dice_red).setVisibility(View.VISIBLE);break;
        }
        Thread myBackgroundThread = new Thread(backgroundRollDice, "rollDice");
        myBackgroundThread.start();
    }

    private Runnable foregroundRollDice = new Runnable() {
        @Override
        public void run() {
            try {
                Random generator = new Random();
                switch (getTurn()){
                    case 1: fragYDice.setImgDice1(generator.nextInt(18)+1);
                        fragYDice.setImgDice2(generator.nextInt(18)+1);
                        break;
                    case 2: fragBDice.setImgDice1(generator.nextInt(18)+1);
                        fragBDice.setImgDice2(generator.nextInt(18)+1);
                        break;
                    case 3: fragGDice.setImgDice1(generator.nextInt(18)+1);
                        fragGDice.setImgDice2(generator.nextInt(18)+1);
                        break;
                    case 4: fragRDice.setImgDice1(generator.nextInt(18)+1);
                        fragRDice.setImgDice2(generator.nextInt(18)+1);
                        break;
                    default:
                }
            }
            catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    };
    private Runnable resultRollDice = new Runnable() {
        @Override
        public void run() {
            try {
                switch (getTurn()){
                    case 1: fragYDice.setImgDice1(resRollDiceOne); fragYDice.setImgDice2(resRollDiceTwo); break;
                    case 2: fragBDice.setImgDice1(resRollDiceOne); fragBDice.setImgDice2(resRollDiceTwo); break;
                    case 3: fragGDice.setImgDice1(resRollDiceOne); fragGDice.setImgDice2(resRollDiceTwo); break;
                    case 4: fragRDice.setImgDice1(resRollDiceOne); fragRDice.setImgDice2(resRollDiceTwo); break;
                }
                //đi quân



                //

                findViewById(R.id.frg_dice_yellow).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_blue).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_green).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_red).setVisibility(View.INVISIBLE);
            }
            catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    };
    private Runnable backgroundRollDice = new Runnable() {
        @Override
        public void run() {
            try {
                int loop = 0;
                int jumb = 300;
                while (loop<=speed){
                    Thread.sleep(jumb);
                    if(loop<=speed/2){
                        jumb = Math.max((jumb / 2), speed / 100);
                    }else {
                        jumb = (jumb*2);
                    }
                    loop += jumb;
                    myHandler.post(foregroundRollDice);
                }
                myHandler.post(resultRollDice);
            }
            catch (InterruptedException e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    };

    //--------------------------------------------------------------------------------1712275
}
