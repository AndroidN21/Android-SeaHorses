package hcmus.nhom21.parcheesigame;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Random;

import hcmus.nhom21.handle.ChessBoard;
import hcmus.nhom21.handle.Database;
import hcmus.nhom21.handle.Horse;
import hcmus.nhom21.handle.Tuple;
import hcmus.nhom21.handle.User;

import static java.lang.String.valueOf;

public class RunningGameActivity extends FragmentActivity implements View.OnClickListener {
    private Button btnTypePlayer;
    private Button btnSetting;
    private ImageView imgBoard;
    private Button btnProfile;
    private boolean flagTypePlayer = false;
    private ImageView imgChat0;
    private ImageView imgChat1;
    private ImageView imgChat2;
    Animation chatAnim0;
    Animation chatAnim1;
    Animation chatAnim2;
    MediaPlayer song;
    private SettingFragment newSettingFragment;
    FragmentTransaction ft;
    Integer arrChatImgs[] = {R.drawable.ic_wow, R.drawable.ic_haha, R.drawable.ic_why_so_serious, R.drawable.ic_oh, R.drawable.ic_like, R.drawable.ic_nope};
    private int lengthArr = arrChatImgs.length;

    final int MESSAGE_RESULT_DICE = 10001;
    final int MESSAGE_POSITION_HORSE = 10002;
    final int MESSAGE_USER_WIN = 10003;
    final int MESSAGE_CHANGE_BUTTON_ROLL = 10004;
    final int MESSAGE_OPACITY_HORSE = 10005;
    final int MESSAGE_START_GAME = 10006;

    private int[] LOCATE_BOARD = new int[2];
    private int[] SIZE_BOARD = new int[2];
    private int[] SIZE_HORSE = new int[2];
    private ChessBoard chessBoard;
    private ArrayList<Integer> horseValid;
    Database database;
    private Handler mhandler;
    Boolean isPause;

    //private ImageView imgDice;
    private int[][] locHorseDefault = new int[16][2];
    private ArrayList<ImageView> imgHorse;
    SharedPreferences sharedPreferences;
    boolean flagHide;
    //SettingFragment settingFragment;

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

    public ArrayList<User> listUser;       // gỡ cmt khi merge

    static private int speed = 2000;
    Handler myHandler = new Handler(Looper.getMainLooper());
    private int resRollDiceOne = 1;
    private int resRollDiceTwo = 1;

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
        // gỡ cmt sau khi merge. Bỏ cmt => error

        switch (turn) {
            case 1:
                viewProfile.setBackgroundResource(R.drawable.ic_profile_yellow);
                break;
            case 2:
                viewProfile.setBackgroundResource(R.drawable.ic_profile_blue);
                break;
            case 3:
                viewProfile.setBackgroundResource(R.drawable.ic_profile_green);
                break;
            case 4:
                viewProfile.setBackgroundResource(R.drawable.ic_profile_red);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runninggame);
        Intent intentMain = getIntent();

        initView();
        initOnClick();
        initHandler();
        //Tạo database trò chơi
        database = new Database(this, "parchessi.sqlite", null, 1);
        sharedPreferences = getSharedPreferences("dataLoadGame", MODE_PRIVATE);
        isPause=false;

        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        imgChat0 = (ImageView) findViewById(R.id.imgChat0);
        imgChat1 = (ImageView) findViewById(R.id.imgChat1);
        imgChat2 = (ImageView) findViewById(R.id.imgChat2);


        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frg_dice_yellow, fragYDice);
        ft.replace(R.id.frg_dice_blue, fragBDice);
        ft.replace(R.id.frg_dice_green, fragGDice);
        ft.replace(R.id.frg_dice_red, fragRDice);
        ft.replace(R.id.frg_dice_result, fragResultRollDice);
        ft.addToBackStack(null);
        ft.commit();

        viewProfile = findViewById(R.id.txtProfile);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onPause() {
        super.onPause();
        isPause=true;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("hasLoadGame", true);
        editor.apply();
        chessBoard.saveChessBoard();
        System.out.println("PauseGame");
        mhandler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause=false;
    }

    @Override
    public void onBackPressed() { }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            btnSetting.setVisibility(View.VISIBLE);
        }
        return super.onKeyDown(keyCode, event);
    }

//    static class ViewHolder{
//        Button btnTypePlayer;
//        ImageButton btnSetting;
//        Image imgBoard;
//        Button btnProfile;
//    }

    //Chuyển sang màn hình thắng hoặc thua
//    void handleEndGame(View view, boolean isWin, String teamName){
//        //Thắng
//        if(isWin){
//            Intent winGameIntent = new Intent(view.getContext(), WinGameActivity.class);
//            winGameIntent.putExtra("teamName", teamName);
//            startActivity(winGameIntent);
//        }
//        //Thua chỉ trong trường hợp đấu với máy
//        //Nếu ng chơi chuyển sang tự động thì tính theo ...
//        else{
//            Intent loseGameIntent = new Intent(view.getContext(), LoseGameActivity.class);
//            loseGameIntent.putExtra("teamName", teamName);
//            startActivity(loseGameIntent);
//        }
//    }

    public void initView() {
        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        imgBoard = (ImageView) findViewById(R.id.imgBoard);
        btnRoll = findViewById(R.id.btn_roll); //btn để test

        //imgDice = (ImageView) findViewById(R.id.imgDice);
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

    }

    public void initOnClick() {
        btnTypePlayer.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        imgBoard.setOnClickListener(this);
        btnRoll.setOnClickListener(this);
        findViewById(R.id.view_result_roll_dice).setOnClickListener(this);

        for (int i = 0; i < 16; i++) {
            imgHorse.get(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        int idLogic = -1;
        switch (v.getId()) {
            case R.id.btnSetting:
                ft = getSupportFragmentManager().beginTransaction();
                btnSetting.setVisibility(View.INVISIBLE);

                newSettingFragment = new SettingFragment();
                ft.replace(R.id.frameSetting, newSettingFragment, "settingFragment");
                ft.addToBackStack(null);
                ft.commit();

                getSupportFragmentManager().executePendingTransactions();
                final SettingFragment settingFragment = (SettingFragment) getSupportFragmentManager().findFragmentByTag("settingFragment");

                //Volume
                if (settingFragment != null) {

                    if (settingFragment.view.findViewById(R.id.item1) != null) {
                        final AudioManager audioManager = (AudioManager) getApplicationContext().getSystemService(AUDIO_SERVICE);
                        final ImageView imgVolume = (ImageView) settingFragment.view.findViewById(R.id.imgVolume);

                        if (!audioManager.isStreamMute(AudioManager.STREAM_MUSIC)) {
                            imgVolume.setImageResource(R.drawable.ic_baseline_volume_up_24);
                        } else {
                            imgVolume.setImageResource(R.drawable.ic_baseline_volume_off_24);
                        }

                    }

                }
                break;
            case R.id.btnTypePlayer:
                if (!flagTypePlayer) {
                    flagTypePlayer = true;
                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_android_24);
                    Toast.makeText(hcmus.nhom21.parcheesigame.RunningGameActivity.this, "Bật tự động chơi", Toast.LENGTH_SHORT).show();
                } else {
                    flagTypePlayer = false;
                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_person_24);
                    Toast.makeText(hcmus.nhom21.parcheesigame.RunningGameActivity.this, "Tắt tự động chơi", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.view_result_roll_dice:
                findViewById(R.id.view_result_roll_dice).setVisibility(View.INVISIBLE);
                findViewById(R.id.view_result_roll_dice).setClickable(false);
                break;
            case R.id.btn_roll:
                btnRoll.setClickable(false);
                break;
            case R.id.imgHorse00:
                idLogic = 0;
                break;
            case R.id.imgHorse01:
                idLogic = 1;
                break;
            case R.id.imgHorse02:
                idLogic = 2;
                break;
            case R.id.imgHorse03:
                idLogic = 3;
                break;
            case R.id.imgHorse10:
                idLogic = 4;
                break;
            case R.id.imgHorse11:
                idLogic = 5;
                break;
            case R.id.imgHorse12:
                idLogic = 6;
                break;
            case R.id.imgHorse13:
                idLogic = 7;
                break;
            case R.id.imgHorse20:
                idLogic = 8;
                break;
            case R.id.imgHorse21:
                idLogic = 9;
                break;
            case R.id.imgHorse22:
                idLogic = 10;
                break;
            case R.id.imgHorse23:
                idLogic = 11;
                break;
            case R.id.imgHorse30:
                idLogic = 12;
                break;
            case R.id.imgHorse31:
                idLogic = 13;
                break;
            case R.id.imgHorse32:
                idLogic = 14;
                break;
            case R.id.imgHorse33:
                idLogic = 15;
                break;
            default:
                break;
        }
        //System.out.println("Horse (idLogic): "+idLogic+"\n");
        if (idLogic != -1) {
            int userTurn = chessBoard.getUserTurn();
            for (int idHorse = 0; idHorse < horseValid.size(); idHorse++) {
                if (idLogic == (userTurn * 4 + horseValid.get(idHorse))) {
                    chessBoard.setHorseTurn(horseValid.get(idHorse));
                    try {
                        HandleMove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }


    public void initHandler() {
        mhandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_START_GAME:
                        startGame();
                        break;
                    case MESSAGE_RESULT_DICE:
                        try {
                            rollDice();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    case MESSAGE_POSITION_HORSE:
                        //System.out.println("OK DI CHUYEN THANH CONG \n");
                        chessBoard.updateChessBoard();
                        break;
                    case MESSAGE_USER_WIN:
                        int userWin = msg.arg1;
                        User user = chessBoard.getUser(userWin);
                        String teamName = "";
                        if (user.getMode() == User.MODE_BOOT) {
                            Intent intentLosingGame = new Intent(RunningGameActivity.this, LoseGameActivity.class);
                            startActivity(intentLosingGame);
                        } else {
                            Intent intentWinningGame = new Intent(RunningGameActivity.this, WinGameActivity.class);
                            switch (userWin) {
                                case 0:
                                    teamName = "Yellow";
                                    break;
                                case 1:
                                    teamName = "Red";
                                    break;
                                case 2:
                                    teamName = "Blue";
                                    break;
                                case 3:
                                    teamName = "Green";
                                    break;
                            }
                            intentWinningGame.putExtra("teamName", teamName);
                            startActivity(intentWinningGame);
                        }
                        break;
                    case MESSAGE_CHANGE_BUTTON_ROLL:
                        setColorButtonRoll();
                        break;
                    case MESSAGE_OPACITY_HORSE:
                        setOpacityHorse(msg.arg1==1);
                        break;
                    default:
                        break;
                }
            }
        };
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //Lấy dữ liệu setup bàn cờ
        Intent intent = getIntent();
        int[] arrSetupPlayer = intent.getIntArrayExtra("arrSetupPlayer");

        //Lấy tọa độ bàn cờ
        imgBoard.getLocationOnScreen(LOCATE_BOARD);

        //Lấy kích thước bàn cờ
//        SIZE_BOARD[0] = imgBoard.getDrawable().getIntrinsicWidth();
//        SIZE_BOARD[1] = imgBoard.getDrawable().getIntrinsicHeight();
        SIZE_BOARD[0] = imgBoard.getMeasuredWidth();
        SIZE_BOARD[1] = imgBoard.getMeasuredHeight();


        //Lấy kích thước ngựa
        SIZE_HORSE[0] = imgHorse.get(0).getMeasuredWidth();
        SIZE_HORSE[1] = imgHorse.get(0).getMeasuredHeight();

        chessBoard = new ChessBoard(arrSetupPlayer, new Tuple(LOCATE_BOARD[0], LOCATE_BOARD[1]), new Tuple(SIZE_BOARD[0], SIZE_BOARD[1]),
                new Tuple(SIZE_HORSE[0], SIZE_HORSE[1]), database);
        horseValid = new ArrayList<Integer>();
        chessBoard.initChessBoard(imgHorse);
        if(!isPause) {
            Message msgStartGame = new Message();
            msgStartGame.what = MESSAGE_START_GAME;
            mhandler.sendMessage(msgStartGame);
        }

    }

    public void startGame(){


        if (sharedPreferences.getBoolean("hasLoadGame", false)) {
            System.out.println("LoadGame");
            Toast.makeText(this, "Load game", Toast.LENGTH_SHORT).show();

            SharedPreferences.Editor editor= sharedPreferences.edit();
            editor.putBoolean("hasLoadGame",false);
            editor.apply();

            chessBoard.loadChessBoard();
        }

        Message msgChangeButton = new Message();
        msgChangeButton.what = MESSAGE_CHANGE_BUTTON_ROLL;
        mhandler.sendMessage( msgChangeButton);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isPause) {
                    if (horseValid.size() <= 0) {
                        User user = chessBoard.getUser();
                        if (user.getMode() == User.MODE_BOOT || (user.getMode() == User.MODE_USER & !btnRoll.isClickable())) {
                            //if (user.getMode() != User.MODE_NONE) {
                            Tuple dice = chessBoard.rollDice();
                            resRollDiceOne = 2 * 6 + dice.x;
                            resRollDiceTwo = 1 * 6 + dice.y;

                            Message msgRollDice = new Message();
                            msgRollDice.what = MESSAGE_RESULT_DICE;
                            mhandler.sendMessage(msgRollDice);

                            horseValid = chessBoard.generateHorseValid();

                        } else if (user.getMode() == User.MODE_NONE) {
                            chessBoard.setUserTurn((chessBoard.getUserTurn() + 1) % 4);
                        }
                    }
                }
            }
        });
        thread.start();

        if(isPause) thread.stop();
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
//            //Toast.makeText(RunningGameActivity.this, "Kill running", Toast.LENGTH_SHORT).show();
//            if (flagHide) {
//                //flagHide = false;
//                findViewById(R.id.imgBoard).setVisibility(View.VISIBLE);
//                findViewById(R.id.txtProfile).setVisibility(View.VISIBLE);
//                findViewById(R.id.btnSetting).setVisibility(View.VISIBLE);
//            }
//            //finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public void HandleMove() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message msgOpacityHorse = new Message();
                msgOpacityHorse.what = MESSAGE_OPACITY_HORSE;
                msgOpacityHorse.arg1 = 0;
                mhandler.sendMessage(msgOpacityHorse);

                final Tuple idPair = new Tuple();
                Tuple errorConflict = new Tuple(0, 0);
                final int step = chessBoard.getStep();
                final User user = chessBoard.getUser();
                final Horse horse = user.getHorse(chessBoard.getHorseTurn());
                errorConflict = chessBoard.checkConflict(horse, step);
                if (horse.getStatus() == 0) {
                    chessBoard.XuatChuong();

                    btnRoll.setClickable(true);

                    horseValid.clear();
                    if (!chessBoard.isRepeat()) {
                        chessBoard.setUserTurn((chessBoard.getUserTurn() + 1) % 4);
                        Message message = new Message();
                        message.what = MESSAGE_CHANGE_BUTTON_ROLL;
                        mhandler.sendMessage(message);
                    }
                } else {
                    final Tuple finalErrorConflict = errorConflict;
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Xu ly nguoi dung chon ngua trong mang ngua vua nay
                            for (int i = step - 1; i >= 0; i--) {
                                if (finalErrorConflict.y == 1 && i == 0) {
                                    chessBoard.Dangua(finalErrorConflict.x);
                                }
                                chessBoard.moveHorse(1);

                                Message message = new Message();
                                message.what = MESSAGE_POSITION_HORSE;
                                mhandler.sendMessage(message);
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                chessBoard.setStep(i);
                            }
                            if (user.checkWin()) {
                                Message message = new Message();
                                message.arg1 = user.getIdUser();
                                message.what = MESSAGE_USER_WIN;
                                mhandler.sendMessage(message);
                            }

                            btnRoll.setClickable(true);
                            horseValid.clear();
                            if (!chessBoard.isRepeat()) {
                                chessBoard.setUserTurn((chessBoard.getUserTurn() + 1) % 4);

                                Message message = new Message();
                                message.what = MESSAGE_CHANGE_BUTTON_ROLL;
                                mhandler.sendMessage(message);

                            }
                        }
                    });
                    thread.start();
                }
            }
        });
        thread.start();
        thread.join();
    }


    private void rollDice() throws InterruptedException {
        int turn = chessBoard.getUserTurn();
        User user = chessBoard.getUser(turn);
        for (int idHorse = 0; idHorse < 4; idHorse++) {
            if (user.getHorse(idHorse).getStatus() == 0)
                imgHorse.get(turn * 4 + idHorse).setVisibility(View.INVISIBLE);
        }
        switch (turn + 1) {
            case 1:
                findViewById(R.id.frg_dice_yellow).setVisibility(View.VISIBLE);
                break;
            case 2:
                findViewById(R.id.frg_dice_red).setVisibility(View.VISIBLE);
                break;
            case 3:
                findViewById(R.id.frg_dice_blue).setVisibility(View.VISIBLE);
                break;
            case 4:
                findViewById(R.id.frg_dice_green).setVisibility(View.VISIBLE);
                break;

        }
        Thread myBackgroundThread = new Thread(backgroundRollDice, "rollDice");
        myBackgroundThread.start();
    }

    private Runnable foregroundRollDice = new Runnable() {
        @Override
        public void run() {
            try {
                Random generator = new Random();
                int num1,num2;
                num1=generator.nextInt(18) + 1;
                num2=generator.nextInt(18) + 1;
                switch (chessBoard.getUserTurn() + 1) {
                    case 1:
                        fragYDice.setImgDice1(num1);
                        fragYDice.setImgDice2(num2);
                        break;
                    case 2:
                        fragRDice.setImgDice1(num1);
                        fragRDice.setImgDice2(num2);
                        break;
                    case 3:
                        fragBDice.setImgDice1(num1);
                        fragBDice.setImgDice2(num2);
                        break;
                    case 4:
                        fragGDice.setImgDice1(num1);
                        fragGDice.setImgDice2(num2);
                        break;
                    default:
                }
            } catch (Exception e) {
                Log.e("<<foregroundTask>>", e.getMessage());
            }
        }
    };
    private Runnable resultRollDice = new Runnable() {
        @Override
        public void run() {

            try {

                int turn = chessBoard.getUserTurn();

                fragResultRollDice.setImgDice1(resRollDiceOne);
                fragResultRollDice.setImgDice2(resRollDiceTwo);
                findViewById(R.id.view_result_roll_dice).setVisibility(View.VISIBLE);
                findViewById(R.id.view_result_roll_dice).setClickable(true);
                //đi quân


                findViewById(R.id.frg_dice_yellow).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_blue).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_green).setVisibility(View.INVISIBLE);
                findViewById(R.id.frg_dice_red).setVisibility(View.INVISIBLE);

                //Thread.sleep(500);

                for (int idHorse = 0; idHorse < 4; idHorse++) {
                    imgHorse.get(turn * 4 + idHorse).setVisibility(View.VISIBLE);
                }
                setOpacityHorse(true);

                //Thread.sleep(500);

                //if (chessBoard.getUser().getMode() == User.MODE_BOOT) {
                if (horseValid.size() <= 0 && !chessBoard.isRepeat()) {
                    chessBoard.setUserTurn((chessBoard.getUserTurn() + 1) % 4);

                    Message message = new Message();
                    message.what = MESSAGE_CHANGE_BUTTON_ROLL;
                    mhandler.sendMessage(message);

                    btnRoll.setClickable(true);
                }
                else if (chessBoard.getUser().getMode() == User.MODE_BOOT){
                    findViewById(R.id.view_result_roll_dice).performClick();
                    for (int i = 0; i < horseValid.size(); i++) {
                        chessBoard.setHorseTurn(horseValid.get(i));
                        try {
                            HandleMove();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            } catch (Exception e) {
                Log.e("<<foregroundTask>>", e.getMessage());
            }
        }
    };

    private Runnable backgroundRollDice = new Runnable() {
        @Override
        public void run() {
            try {
                int loop = 0;
                int jumb = 300;
                while (loop <= speed) {
                    Thread.sleep(jumb);
                    if (loop <= speed) {
                        jumb = Math.max((jumb / 2), speed / 20);
                    } else {
                        jumb = (jumb * 2);
                    }
                    loop += jumb;

                    myHandler.post(foregroundRollDice);
                }
                myHandler.post(resultRollDice);

            } catch (InterruptedException e) {
                Log.e("<<foregroundTask>>", e.getMessage());
            }
        }
    };

    public void setColorButtonRoll() {
        switch (chessBoard.getUserTurn()) {
            case 0:
                btnRoll.setBackgroundColor(getResources().getColor(R.color.yellowTeamBackground));
                break;
            case 1:
                btnRoll.setBackgroundColor(getResources().getColor(R.color.redTeamBackground));
                break;
            case 2:
                btnRoll.setBackgroundColor(getResources().getColor(R.color.blueTeamBackground));
                break;
            case 3:
                btnRoll.setBackgroundColor(getResources().getColor(R.color.greenTeamBackground));
                break;
        }
    }

    public void setOpacityHorse(boolean isOpacity){
        int turn=chessBoard.getUserTurn();
        if(isOpacity){
            for(int i=0;i<4;i++){
                if(horseValid.indexOf(i)==-1) {
                    imgHorse.get(turn * 4 + i).setAlpha((float) 0.7);
                }
            }
        }
        else{
            for(int i=0;i<4;i++){
                imgHorse.get(turn*4 +i).setAlpha((float) 1);
            }
        }
    }
}
