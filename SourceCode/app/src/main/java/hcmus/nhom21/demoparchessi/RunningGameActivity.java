package hcmus.nhom21.demoparchessi;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class RunningGameActivity extends FragmentActivity{
    private Button btnTypePlayer;
    private ImageButton btnSetting;
    private Image imgBoard;
    private Button btnProfile;
    boolean flagTypePlayer = false;
    boolean flagHide = false;

    FragmentTransaction ft;

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
}
