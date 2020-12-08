package hcmus.nhom21.demoparchessi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
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
    FragmentDice fragResultRollDice = new FragmentDice();


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

    private int[][] locHorseDefault = new int[16][2]; // yellow[0-3] blue[4-7] green[8-11] red[12-15]
    private ArrayList<ImageView> imgHorse;


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

        //set player
        Intent intentMain = getIntent();
        String playerType = intentMain.getStringExtra("setupPlayer");
        String players[] = playerType.split("#");
        for (int i= 0; i<players.length;i++){
            switch (players[i]){
                case "Player": //set user[i] = player
                    break;
                case "Boot": //set user[i] = boot
                    break;
                case "None": //set user[i] = none
                    break;
                default:
            }
        }


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
        ft.replace(R.id.frg_dice_result,fragResultRollDice);
        ft.addToBackStack(null);
        ft.commit();
        btnRoll = findViewById(R.id.btn_roll); //btn để test
        btnRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int[] location = new int[2];
                findViewById(R.id.img_yhorse1).getLocationInWindow(location);
                Log.e("mylog",location[0]+" "+location[1]);
                Log.e("mylog",locHorseDefault[0][0]+" "+locHorseDefault[0][1]);
                Random generator = new Random();
                setTurn(generator.nextInt(4)+1);
                rollDice(getTurn());
            }
        });

        viewProfile = findViewById(R.id.txtProfile);

        imgHorse = new ArrayList<ImageView>(16);

        imgHorse.add((ImageView) findViewById(R.id.imgHorse00));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse01));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse02));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse03));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse10));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse11));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse12));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse13));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse20));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse21));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse22));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse23));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse30));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse31));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse32));
        imgHorse.add((ImageView) findViewById(R.id.imgHorse33));


        for (ImageView imgv:imgHorse){
            imgv.setX(-35);
            imgv.setX(-50);
        }

        Thread startThread = new Thread(backgroundStartGameAnimation, "start");
        startThread.start();
        findViewById(R.id.view_result_roll_dice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.view_result_roll_dice).setVisibility(View.INVISIBLE);
                btnRoll.setVisibility(View.VISIBLE);
                findViewById(R.id.frg_dice_yellow).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_blue).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_green).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_red).setVisibility(View.INVISIBLE);
                //đi quân ở đây



                //

            }
        });
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
            else {
                //save game

                //
                finish();
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
        kickHorse(200,400,3);
        outStables(300,500,8);
    }
    private Runnable backgroundKickHorseThread;
    private Runnable foregroundKickHorseThread;
    private Runnable backgroundOutStablesThread;
    private Runnable foregroundOutStablesThread;
    private int alphaImage = 0;

    //xuất chuồng x y là toạ độ điểm xuất, index là vị trí ngựa trong mảng
    private void outStables(int x, int y, final int indexHorse) {
        int locationScreen[] = new int[2];
        findViewById(R.id.linearLayout).getLocationOnScreen(locationScreen);

        imgHorse.get(indexHorse).setImageAlpha(0);
        imgHorse.get(indexHorse).setX(x-locationScreen[0]);
        imgHorse.get(indexHorse).setY(y-locationScreen[1]);

        backgroundOutStablesThread = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 8; i< 256; i= i+8){
                        Thread.sleep(32);
                        alphaImage = i-1;
                        myHandler.post(foregroundOutStablesThread);
                    }
                }
                catch (Exception e) { Log.e("<<BackgroundTask>>", e.getMessage()); }
            }
        };
        foregroundOutStablesThread = new Runnable() {
            @Override
            public void run() {
                try {
                    final int index = indexHorse;
                    imgHorse.get(index).setImageAlpha(alphaImage);
                }
                catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
            }
        };
        Thread kickThread = new Thread(backgroundOutStablesThread, "kickHorse");
        kickThread.start();
    }


    //xuất chuồng x y  toạ độ quân bị đá, index là vị trí ngựa trong mảng
    private void kickHorse(int x, int y, final int indexHorse) {
        int locationScreen[] = new int[2];
        findViewById(R.id.linearLayout).getLocationOnScreen(locationScreen);

        imgHorse.get(indexHorse).setImageAlpha(0);
        imgHorse.get(indexHorse).setX(locHorseDefault[indexHorse][0]-locationScreen[0]);
        imgHorse.get(indexHorse).setY(locHorseDefault[indexHorse][1]-locationScreen[1]);

        ImageView horseGray;
        switch (indexHorse){
            case 0: horseGray = findViewById(R.id.imgHorseDie00); break;
            case 1: horseGray = findViewById(R.id.imgHorseDie01); break;
            case 2: horseGray = findViewById(R.id.imgHorseDie02); break;
            case 3: horseGray = findViewById(R.id.imgHorseDie03); break;
            case 4: horseGray = findViewById(R.id.imgHorseDie10); break;
            case 5: horseGray = findViewById(R.id.imgHorseDie11); break;
            case 6: horseGray = findViewById(R.id.imgHorseDie12); break;
            case 7: horseGray = findViewById(R.id.imgHorseDie13); break;
            case 8: horseGray = findViewById(R.id.imgHorseDie20); break;
            case 9: horseGray = findViewById(R.id.imgHorseDie21); break;
            case 10: horseGray = findViewById(R.id.imgHorseDie22); break;
            case 11: horseGray = findViewById(R.id.imgHorseDie23); break;
            case 12: horseGray = findViewById(R.id.imgHorseDie30); break;
            case 13: horseGray = findViewById(R.id.imgHorseDie31); break;
            case 14: horseGray = findViewById(R.id.imgHorseDie32); break;
            case 15: horseGray = findViewById(R.id.imgHorseDie33); break;
            default:
                throw new IllegalStateException("Unexpected value: " + indexHorse);
        }
        horseGray.setVisibility(View.VISIBLE);
        horseGray.setImageAlpha(255);
        horseGray.setX(x - locationScreen[0]);
        horseGray.setY(y - locationScreen[1]);
        backgroundKickHorseThread = new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 8; i< 256; i= i+8){
                        Thread.sleep(32);
                        alphaImage = i-1;
                        myHandler.post(foregroundKickHorseThread);
                    }
                }
                catch (Exception e) { Log.e("<<BackgroundTask>>", e.getMessage()); }
            }
        };
        foregroundKickHorseThread = new Runnable() {
            @Override
            public void run() {
                try {
                    final int index = indexHorse;
                    imgHorse.get(index).setImageAlpha(alphaImage);
                    switch (indexHorse){
                        case 0: ((ImageView)findViewById(R.id.imgHorseDie00)).setImageAlpha(255-alphaImage); break;
                        case 1: ((ImageView)findViewById(R.id.imgHorseDie01)).setImageAlpha(255-alphaImage); break;
                        case 2: ((ImageView)findViewById(R.id.imgHorseDie02)).setImageAlpha(255-alphaImage); break;
                        case 3: ((ImageView)findViewById(R.id.imgHorseDie03)).setImageAlpha(255-alphaImage); break;
                        case 4: ((ImageView)findViewById(R.id.imgHorseDie10)).setImageAlpha(255-alphaImage); break;
                        case 5: ((ImageView)findViewById(R.id.imgHorseDie11)).setImageAlpha(255-alphaImage); break;
                        case 6: ((ImageView)findViewById(R.id.imgHorseDie12)).setImageAlpha(255-alphaImage); break;
                        case 7: ((ImageView)findViewById(R.id.imgHorseDie13)).setImageAlpha(255-alphaImage); break;
                        case 8: ((ImageView)findViewById(R.id.imgHorseDie20)).setImageAlpha(255-alphaImage); break;
                        case 9: ((ImageView)findViewById(R.id.imgHorseDie21)).setImageAlpha(255-alphaImage); break;
                        case 10: ((ImageView)findViewById(R.id.imgHorseDie22)).setImageAlpha(255-alphaImage); break;
                        case 11: ((ImageView)findViewById(R.id.imgHorseDie23)).setImageAlpha(255-alphaImage); break;
                        case 12: ((ImageView)findViewById(R.id.imgHorseDie30)).setImageAlpha(255-alphaImage); break;
                        case 13: ((ImageView)findViewById(R.id.imgHorseDie31)).setImageAlpha(255-alphaImage); break;
                        case 14: ((ImageView)findViewById(R.id.imgHorseDie32)).setImageAlpha(255-alphaImage); break;
                        case 15: ((ImageView)findViewById(R.id.imgHorseDie33)).setImageAlpha(255-alphaImage); break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + indexHorse);
                    }
                }
                catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
            }
        };
        Thread kickThread = new Thread(backgroundKickHorseThread, "kickHorse");
        kickThread.start();
    }

    private Runnable foregroundShowHorse = new Runnable() {
        @Override
        public void run() {
            try {
                findViewById(R.id.img_yhorse1).getLocationOnScreen(locHorseDefault[0]);
                findViewById(R.id.img_yhorse2).getLocationOnScreen(locHorseDefault[1]);
                findViewById(R.id.img_yhorse3).getLocationOnScreen(locHorseDefault[2]);
                findViewById(R.id.img_yhorse4).getLocationOnScreen(locHorseDefault[3]);
                findViewById(R.id.img_bhorse1).getLocationOnScreen(locHorseDefault[4]);
                findViewById(R.id.img_bhorse2).getLocationOnScreen(locHorseDefault[5]);
                findViewById(R.id.img_bhorse3).getLocationOnScreen(locHorseDefault[6]);
                findViewById(R.id.img_bhorse4).getLocationOnScreen(locHorseDefault[7]);
                findViewById(R.id.img_ghorse1).getLocationOnScreen(locHorseDefault[8]);
                findViewById(R.id.img_ghorse2).getLocationOnScreen(locHorseDefault[9]);
                findViewById(R.id.img_ghorse3).getLocationOnScreen(locHorseDefault[10]);
                findViewById(R.id.img_ghorse4).getLocationOnScreen(locHorseDefault[11]);
                findViewById(R.id.img_rhorse1).getLocationOnScreen(locHorseDefault[12]);
                findViewById(R.id.img_rhorse2).getLocationOnScreen(locHorseDefault[13]);
                findViewById(R.id.img_rhorse3).getLocationOnScreen(locHorseDefault[14]);
                findViewById(R.id.img_rhorse4).getLocationOnScreen(locHorseDefault[15]);
                int i = 0;
                int locationScreen[] = new int[2];
                findViewById(R.id.linearLayout).getLocationOnScreen(locationScreen);
                for (ImageView imgv:imgHorse){
                    imgv.setX(locHorseDefault[i][0]-locationScreen[0]);
                    imgv.setY(locHorseDefault[i++][1]-locationScreen[1]);
                    imgv.setImageAlpha(alphaImage);

                }
            }
            catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    };

    private Runnable backgroundStartGameAnimation = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i = 8; i< 256; i= i+4){
                    alphaImage = i-1;
                    myHandler.post(foregroundShowHorse);
                    Thread.sleep(16);
                }
            }
            catch (Exception e) { Log.e("<<foregroundTask>>", e.getMessage()); }
        }
    };
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
                //kết quả
                fragResultRollDice.setImgDice1(resRollDiceOne);
                fragResultRollDice.setImgDice2(resRollDiceTwo);
                findViewById(R.id.view_result_roll_dice).setVisibility(View.VISIBLE);
                btnRoll.setVisibility(View.INVISIBLE);
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
