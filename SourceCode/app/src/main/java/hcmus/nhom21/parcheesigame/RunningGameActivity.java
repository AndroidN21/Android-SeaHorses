package hcmus.nhom21.parcheesigame;

import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
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

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import java.util.Random;

import static java.lang.String.valueOf;

public class RunningGameActivity extends FragmentActivity {
    private Button btnTypePlayer;
    private Button btnSetting;
    private Image imgBoard;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runninggame);
        //Intent intentMain = getIntent();
        //song = MediaPlayer.create(RunningGameActivity.this, R.raw.a1);

        btnTypePlayer = (Button) findViewById(R.id.btnTypePlayer);
        btnSetting = (Button) findViewById(R.id.btnSetting);
        imgChat0 = (ImageView) findViewById(R.id.imgChat0);
        imgChat1 = (ImageView) findViewById(R.id.imgChat1);
        imgChat2 = (ImageView) findViewById(R.id.imgChat2);

        //Dùng View Holder
        //ViewHolder viewHolder = new ViewHolder();

        //.setTag(viewHolder);

        //Bật tắt chế độ auto
        btnTypePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!flagTypePlayer) {
                    flagTypePlayer = true;
                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_android_24);
                    Toast.makeText(RunningGameActivity.this, "Bật tự động chơi", Toast.LENGTH_SHORT).show();
                } else {
                    flagTypePlayer = false;
                    btnTypePlayer.setBackgroundResource(R.drawable.ic_baseline_person_24);
                    Toast.makeText(RunningGameActivity.this, "Tắt tự động chơi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //song.text_start();
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

                //Test
                int index = new Random().nextInt(lengthArr);
                imgChat0.setImageResource(arrChatImgs[index]);
                index = new Random().nextInt(lengthArr);
                imgChat1.setImageResource(arrChatImgs[index]);
                index = new Random().nextInt(lengthArr);
                imgChat2.setImageResource(arrChatImgs[index]);

                imgChat0.startAnimation(chatAnim0);
                imgChat1.startAnimation(chatAnim1);
                imgChat2.startAnimation(chatAnim2);

            }
        });

        //Khi đổ xúc sắc xong sẽ hiện chat.
        //Test
        chatAnim0 = AnimationUtils.loadAnimation(this, R.anim.anim_chat0);
        chatAnim1 = AnimationUtils.loadAnimation(this, R.anim.anim_chat0);
        chatAnim2 = AnimationUtils.loadAnimation(this, R.anim.anim_chat1);

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
}
