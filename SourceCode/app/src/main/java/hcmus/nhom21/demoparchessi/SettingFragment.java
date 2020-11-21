package hcmus.nhom21.demoparchessi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.AUDIO_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;
import static java.lang.String.valueOf;

public class SettingFragment extends Fragment{
    RunningGameActivity main;
    Context context = null;
    public View view;
    private ImageButton btnExit;
    private Integer [] arrImgIconTypeSettingOn = {R.drawable.ic_baseline_volume_up_24, R.drawable.ic_vibrate_on, R.drawable.ic_baseline_notes_24};
    private ImageView imgVolume;
    private ImageView imgVibrate;
    private LinearLayout item1;
    private LinearLayout item2;
    private LinearLayout item3;
    private boolean flagVibrate = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity();
            main = (RunningGameActivity) getActivity();
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment_setting, null);

        btnExit = (ImageButton) view.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main.findViewById(R.id.btnSetting).setVisibility(View.VISIBLE);

                //Delete fragment
                Fragment fragment = getFragmentManager().findFragmentById(R.id.frameSetting);
                if (fragment != null) {
                    main.getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                    main.getSupportFragmentManager().popBackStack();
                }
            }
        });

        return view;
    }// onCreateView

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View v = getView();
        item1 = (LinearLayout) v.findViewById(R.id.item1);
        item2 = (LinearLayout) v.findViewById(R.id.item2);
        item3 = (LinearLayout) v.findViewById(R.id.item3);
        imgVolume = (ImageView) v.findViewById(R.id.imgVolume);
        imgVibrate = (ImageView) v.findViewById(R.id.imgVibrate);

        item1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

                if (!audioManager.isStreamMute(AudioManager.STREAM_MUSIC)) {
                    imgVolume.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
                } else {
                    audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
                    imgVolume.setImageResource(R.drawable.ic_baseline_volume_up_24);
                }
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flagVibrate) {
                    flagVibrate = false;
                    imgVibrate.setImageResource(R.drawable.ic_vibrate_off_24);
                    turnOffVibrator();

                } else {
                    flagVibrate = true;
                    imgVibrate.setImageResource(R.drawable.ic_vibrate_on_24);
                    turnOnVibrator();
                }
            }
        });

        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ruleIntent = new Intent();
                ruleIntent.setClass(v.getContext(), RuleActivity.class);
                startActivity(ruleIntent);
            }
        });
    }

    public void turnOffVibrator(){
        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        vibrator.cancel();
    }

    //Turn on vibrator
    public void turnOnVibrator(){
        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        vibrator.hasVibrator();
    }

}// class
