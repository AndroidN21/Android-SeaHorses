package hcmus.nhom21.demoparchessi;


import hcmus.nhom21.handle.Dice;
import hcmus.nhom21.handle.Horse;
import hcmus.nhom21.handle.Tuple;
import hcmus.nhom21.handle.User;

import android.content.Intent;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class RunningGameActivity extends FragmentActivity{
    private int[] LOCATE_BOARD=new int[2];
    private int[] SIZE_BOARD=new int[2];
    final int NUM_USER = 4;
    final int NUM_HORSE=4;
    private ArrayList<User> listUser;
    private ArrayList<Tuple> listIdHorse;// Luu ngua voi status =1 dang duoc chay voi x la idUser va y là idHorse

    private Button btnTypePlayer;
    private ImageButton btnSetting;
    private ImageView imgBoard;
    private ArrayList<ImageView> imgHorse;
    boolean flagTypePlayer = false;
    boolean flagHide;
    SettingFragment settingFragment;

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runninggame);
        Intent intentMain = getIntent();

        AnhXa();

        //Toast.makeText(this, imgBoard.getLocationOnScreen().toString(), Toast.LENGTH_SHORT).show();
        //Bật tắt chế độ auto
        btnTypePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!flagTypePlayer){
                    flagTypePlayer = true;
                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_android_24);
                    Toast.makeText(RunningGameActivity.this, "Bật tự động chơi", Toast.LENGTH_SHORT).show();
                }
                else{
                    flagTypePlayer = false;
                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_person_24);
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
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        listUser = new ArrayList<User>(4);
        listIdHorse=new ArrayList<Tuple>();

        //Lấy tọa độ bàn cờ
        imgBoard.getLocationOnScreen(LOCATE_BOARD);
        //System.out.println("Tọa độ:   " + LOCATE_BOARD[0] + "&&&&" +LOCATE_BOARD[1] + "/n");

        //Lấy kích thước bàn cờ
        SIZE_BOARD[0] = imgBoard.getDrawable().getIntrinsicWidth();
        SIZE_BOARD[1] = imgBoard.getDrawable().getIntrinsicHeight();
        //System.out.println("SIZEBOARD:   " + SIZE_BOARD[0] + "&&&&" +SIZE_BOARD[1] + "/n");

        //Khởi tạo User và những con ngựa nó quản lý
        for (int idUser = 0; idUser < NUM_USER; idUser++) {
            User user=new User(idUser,new Tuple(LOCATE_BOARD[0],LOCATE_BOARD[1]),new Tuple(SIZE_BOARD[0],SIZE_BOARD[1]));
            listUser.add(idUser,user);

            for(int idHorse=0; idHorse<NUM_HORSE;idHorse++){
                user.getListHorse().add(idHorse,new Horse(imgHorse.get(idUser*NUM_HORSE+idHorse), idUser * 14, idUser, idHorse));
                user.resetHorseCoord(idHorse);
            }
        }
        listUser.get(0).setFlag(1);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            //Toast.makeText(RunningGameActivity.this, "Kill running", Toast.LENGTH_SHORT).show();
            if (flagHide){
                //flagHide = false;
                findViewById(R.id.imgBoard).setVisibility(View.VISIBLE);
                findViewById(R.id.txtProfile).setVisibility(View.VISIBLE);
                findViewById(R.id.btnSetting).setVisibility(View.VISIBLE);
            }
            //finish();
        }
        return super.onKeyDown(keyCode, event);
    }


    public void AnhXa(){
        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);
        btnSetting = (ImageButton) findViewById(R.id.btnSetting);
        imgBoard=(ImageView) findViewById(R.id.imgBoard);
        imgHorse=new ArrayList<ImageView>(16);

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

    public boolean Turn(int idUser) {
        Dice dice;
        dice = new Dice(1, 1);// Khoi tao load image
        int step = dice.rollDice();
        Horse horse=null;
        User user=listUser.get(idUser);
        boolean isRepeat=false;
        //Đk đi kèm với select xuat chuong cua nguoi dung
        if (dice.getNumDice1() == dice.getNumDice2() || (step == 7 && (dice.getNumDice1() == 1 || dice.getNumDice1() == 6))) {

            //if() nguoi dung chon
            horse = XuatChuong(idUser);
            if (horse != null) {
                listIdHorse.add(new Tuple(idUser,horse.getIdHorse()));
            }

            isRepeat=true;
        }


        Tuple Id=new Tuple();
        int flagConflict=0;
        if (horse == null) {
            //Khởi tạo mảng ngụa có thể chạy
            ArrayList<Horse> horseValid = new ArrayList<>();
            for (int i = 0; i < NUM_HORSE; i++) {
                horse = user.getHorse(i);
                flagConflict=checkConflict(Id,horse, step);
                if (horse.getStatus() == 1 && flagConflict!=-1 && Id.x!=idUser) {
                    horseValid.add(horse);
                }
            }


            //Xu ly nguoi dung chon ngua trong mang ngua vua nay
            horse.Move(step - 1);

            if(flagConflict==1){
                Dangua(Id);
            }
            horse.Move(1);


        }


        return isRepeat;
    }


    public int checkConflict(Tuple Id,Horse horse,int step){
        for(int i=0; i<listIdHorse.size();i++){
            Horse otherHorse= getHorse(listIdHorse.get(i));
            if(horse.getPosition() + step == otherHorse.getPosition()){
                Id=listIdHorse.get(i);
                return 1;
            }
            if(horse.getPosition() < otherHorse.getPosition() && horse.getPosition() + step > otherHorse.getPosition()) {
                Id=listIdHorse.get(i);
                return -1;
            }
        }
        return 0;
    }

    public void Dangua(Tuple Id){
        Horse horse=getHorse(Id);

        horse.resetInitial();
        for(int i=0;i<listIdHorse.size();i++) {
            if(listIdHorse.get(i).x ==Id.x &&listIdHorse.get(i).y==Id.y ) {
                listIdHorse.remove(i);
                break;
            }
        }
        //listHorse.remove(horse.getPosition() + 1);
    }

    public Horse XuatChuong(int idUser){
        User user=listUser.get(idUser);
        Tuple Id=new Tuple();
        int flag=0;
        for(int i=0;i<NUM_HORSE;i++){
            Horse horse=user.getHorse(i);
            if(horse.getStatus()==0){
                flag=checkConflict(Id,horse,0);

                if(flag!=0){
                    if(Id.x!=idUser) Dangua(Id);
                    else break;
                }
                horse.initialCoord();
                horse.setStatus(1);
                return horse;
            }
        }
        return null;
    }

    public Horse getHorse(Tuple Id){
        User user= listUser.get(Id.x);
        Horse horse= user.getHorse(Id.y);
        return horse;
    }
}
