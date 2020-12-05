package hcmus.nhom21.parcheesigame;

import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
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

    final int MESSAGE_DICE = 10001;
    final int MESSAGE_POSITION_HORSE = 10002;
    private int[] LOCATE_BOARD = new int[2];
    private int[] SIZE_BOARD = new int[2];
    private int[] SIZE_HORSE = new int[2];
    private ChessBoard chessBoard;
    private ArrayList<Integer> horseValid;
    Database database;
    private Handler mhandler;

    private ImageView imgDice;
    private ArrayList<ImageView> imgHorse;
    boolean flagHide;
    //SettingFragment settingFragment;

    FragmentManager fm;
    private View v;

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

        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        imgChat0 = (ImageView) findViewById(R.id.imgChat0);
        imgChat1 = (ImageView) findViewById(R.id.imgChat1);
        imgChat2 = (ImageView) findViewById(R.id.imgChat2);

        //Dùng View Holder
        //ViewHolder viewHolder = new ViewHolder();

        //.setTag(viewHolder);

        //Bật tắt chế độ auto
//        btnTypePlayer.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (!flagTypePlayer) {
//                    flagTypePlayer = true;
//                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_android_24);
//                    Toast.makeText(RunningGameActivity.this, "Bật tự động chơi", Toast.LENGTH_SHORT).show();
//                } else {
//                    flagTypePlayer = false;
//                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_person_24);
//                    Toast.makeText(RunningGameActivity.this, "Tắt tự động chơi", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //song.start();
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

            }
        });

    }


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

    @Override
    public void onClick(View v) {
        int idLogic=-1;
        switch (v.getId()) {
            case R.id.btnSetting:
                ft = getSupportFragmentManager().beginTransaction();
                btnSetting.setVisibility(View.INVISIBLE);

                SettingFragment settingFragment = new SettingFragment();
                ft.replace(R.id.frameSetting, settingFragment);
                ft.addToBackStack(null);
                ft.commit();

                //Ẩn/Vô hiệu hóa/Chèn fragment lên trên cùng của activiy hiện tại
                //findViewById(R.id.imgBoard).setVisibility(View.INVISIBLE);
                //findViewById(R.id.txtProfile).setVisibility(View.INVISIBLE);
                flagHide = true;
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
        System.out.println("Horse (idLogic): "+idLogic+"\n");
        if(idLogic!=-1) {
            int userTurn=chessBoard.getUserTurn();
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

    @Override
    protected void onPause() {
        chessBoard.saveChessBoard();
        super.onPause();
    }

    public void initHandler() {
        mhandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                switch (msg.what) {
                    case MESSAGE_POSITION_HORSE:
                        System.out.println("OK DI CHUYEN THANH CONG \n");
                        chessBoard.updateChessBoard();
                        break;
                    default:
                        break;
                }
            }
        };
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        //Lấy tọa độ bàn cờ

        imgBoard.getLocationOnScreen(LOCATE_BOARD);

        //System.out.println("Tọa độ:   " + LOCATE_BOARD[0] + "&&&&" +LOCATE_BOARD[1] + "/n");

        //Lấy kích thước bàn cờ
        SIZE_BOARD[0] = imgBoard.getDrawable().getIntrinsicWidth();
        SIZE_BOARD[1] = imgBoard.getDrawable().getIntrinsicHeight();
        //System.out.println("SIZE BOARD:   " + SIZE_BOARD[0] + "&&&&" +SIZE_BOARD[1] + "/n");

        //Lấy kích thước ngựa
        SIZE_HORSE[0] = imgHorse.get(0).getMeasuredWidth();
        SIZE_HORSE[1] = imgHorse.get(0).getMeasuredHeight();
        //System.out.println("SIZEHORSE:   " + SIZE_HORSE[0] + "&&&&" +SIZE_HORSE[1] + "\n");
        chessBoard = new ChessBoard(new Tuple(LOCATE_BOARD[0], LOCATE_BOARD[1]), new Tuple(SIZE_BOARD[0], SIZE_BOARD[1]),
                new Tuple(SIZE_HORSE[0], SIZE_HORSE[1]), database);
        horseValid=new ArrayList<Integer>();

        chessBoard.initChessBoard(imgHorse);
        try {
            chessBoard.loadChessBoard();//Lựa chọn  load game nhận intent từ activity trước
            Toast.makeText(this, "Load game", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "init game", Toast.LENGTH_SHORT).show();
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (horseValid.size()<=0) {
                        System.out.println("---------------------------------------------------------- \n");
                        System.out.println("Turn: "+chessBoard.getUserTurn()+"\n");
                        horseValid = chessBoard.Turn();
                    }
                }
            }
        });
        thread.start();
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


    public void initView() {
        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        imgBoard = (ImageView) findViewById(R.id.imgBoard);
        imgDice = (ImageView) findViewById(R.id.imgDice);
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
    public void initOnClick(){
        btnTypePlayer.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        imgBoard.setOnClickListener(this);
        imgDice.setOnClickListener(this);
        for(int i=0;i<16;i++){
            imgHorse.get(i).setOnClickListener(this);
        }
    }


    public void HandleMove() throws InterruptedException {
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {

                final Tuple idPair=new Tuple();
                final int flagConflict;
                final int step=chessBoard.getStep();
                final User user=chessBoard.getUser();
                final Horse horse = user.getHorse(chessBoard.getHorseTurn());
                flagConflict=chessBoard.checkConflict(idPair, horse, step);
                if (horse.getStatus() == 0) {
                    System.out.println("Xuat chuong " + chessBoard.getUserTurn() +"---"+chessBoard.getHorseTurn()+"\n");
                    chessBoard.XuatChuong();
                    chessBoard.getListIdHorse().add(new Tuple(chessBoard.getUserTurn(), horse.getIdHorse()));

                    horseValid.clear();
                    chessBoard.setUserTurn((chessBoard.getUserTurn() + 1) % 4);
                } else {
                    System.out.println("Di chuyen " + chessBoard.getUserTurn() + "---" + chessBoard.getHorseTurn() + "\n");
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //Xu ly nguoi dung chon ngua trong mang ngua vua nay
                            for (int i = step - 1; i >= 0; i--) {
                                //.MoveHorse(horse.getIdHorse(), 1);
                                if (flagConflict == 1 && i==0) {
                                    chessBoard.Dangua(idPair);
                                }
                                chessBoard.moveHorse(1);
                                //
                                Message message = new Message();
                                message.what = MESSAGE_POSITION_HORSE;
                                mhandler.sendMessage(message);
                                try {
                                    Thread.sleep(150);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                chessBoard.setStep(i);
                            }

                            horseValid.clear();
                            chessBoard.setUserTurn((chessBoard.getUserTurn() + 1) % 4);
                        }
                    });
                    thread.start();
//                    try {
//                        thread.join();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }

                }

            }
        });
        thread.start();
        thread.join();
    }
}
