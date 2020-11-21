package hcmus.nhom21.demoparchessi;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import static android.content.Context.AUDIO_SERVICE;
import static android.content.Context.VIBRATOR_SERVICE;

public class CustomTypeSettingAdapter extends ArrayAdapter<String> {
    Context context;
    String[] arrNameTypeSettings;
    Integer[] arrImgIconTypeSettingOn;
    Boolean[] arrTurnOn = {true, true};
    Integer [] arrImgIconTypeSettingOff = {R.drawable.ic_baseline_volume_off_24, R.drawable.ic_vibrate_off, R.drawable.ic_baseline_notes_24};

    public CustomTypeSettingAdapter( Context context, int layoutToBeInflated, Integer[] arrImgIconTypeSetting, String[] arrNameTypeSetting) {
        super(context, R.layout.item_listview_setting, arrNameTypeSetting);
        this.context = context;
        this.arrImgIconTypeSettingOn = arrImgIconTypeSetting;
        this.arrNameTypeSettings = arrNameTypeSetting;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = inflater.inflate(R.layout.item_listview_setting, null);
        TextView txtNameTypeSetting = (TextView) item.findViewById(R.id.txtNameTypeSetting);
        final ImageView imgIconTypeSetting = (ImageView) item.findViewById(R.id.imgIconTypeSetting);
        //Log.e("run", txtNameTypeSetting.getText().toString());
        imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOn[position]);
        txtNameTypeSetting.setText(arrNameTypeSettings[position]);
        Log.e("run", txtNameTypeSetting.getText().toString());
        //arrTurnOn[position] = true;

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 0) {
                    AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

                    if (!audioManager.isStreamMute(AudioManager.STREAM_MUSIC)){
                        Log.e("audio", "tắt âm thanh");
                        imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOff[position]);
                        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
                    } else {
                        Log.e("audio", "bật âm thanh");
                        audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
                        imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOn[position]);
                    }
                }

                if (position == 1) {
//                    if (arrTurnOn[position]) {
//                        arrTurnOn[position] = false;
//                        imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOff[position]);
//
//                        if (position == 0) {
//                            turnOffSound();
//                        } else {
//                            turnOffVibrator();
//                        }
//                    } else {
//                        arrTurnOn[position] = true;
//                        imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOn[position]);
//
//                        if (position == 0) {
//                            turnOnSound();
//                        } else {
//                            turnOnVibrator();
//                        }
//                    }
                    if (arrTurnOn[position]) {
                        arrTurnOn[position] = false;
                        imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOff[position]);

                        turnOffVibrator();
                    } else {
                        arrTurnOn[position] = true;
                        imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOn[position]);

                        turnOnVibrator();
                    }
                }

                if (position == 2){
                    Toast.makeText(context, "Mở luật chơi", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return (item);
    }

    //Turn off sound
    private void turnOffSound() {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

        if (!audioManager.isStreamMute(AudioManager.STREAM_MUSIC)){
            Log.e("audio", "âm thanh tắt");
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
        }
    }

    //Turn on sound
    public void turnOnSound() {
        AudioManager audioManager = (AudioManager) context.getSystemService(AUDIO_SERVICE);

        if (audioManager.isStreamMute(AudioManager.STREAM_MUSIC)){
            Log.e("audio", "âm thanh bật");
            audioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
        }

    }

    //Turn off vibrator
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
}
