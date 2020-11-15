package hcmus.nhom21.demoparchessi;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Vibrator;
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
    Integer[] arrImgIconTypeSettings;
    Boolean[] arrTurnOn = {true, true};
    Integer [] arrImgIconTypeSettingOff = {R.drawable.ic_baseline_volume_off_24, R.drawable.ic_vibrate_off};

    public CustomTypeSettingAdapter( Context context, int layoutToBeInflated, Integer[] arrImgIconTypeSetting, String[] arrNameTypeSetting) {
        super(context, R.layout.item_listview_setting, arrNameTypeSetting);
        this.context = context;
        this.arrImgIconTypeSettings = arrImgIconTypeSetting;
        this.arrNameTypeSettings = arrNameTypeSetting;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View item = inflater.inflate(R.layout.item_listview_setting, null);
        TextView txtNameTypeSetting = (TextView) item.findViewById(R.id.txtNameTypeSetting);
        final ImageView imgIconTypeSetting = (ImageView) item.findViewById(R.id.imgIconTypeSetting);

        imgIconTypeSetting.setImageResource(arrImgIconTypeSettings[position]);
        txtNameTypeSetting.setText(arrNameTypeSettings[position]);
        //arrTurnOn[position] = true;

        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Turn off", Toast.LENGTH_SHORT).show();

                if(arrTurnOn[position]){
                    arrTurnOn[position] = false;
                    imgIconTypeSetting.setImageResource(arrImgIconTypeSettingOff[position]);

                    if(position == 0) {
                        turnOffSound();
                    } else {
                        turnOffVibrator();
                    }
                } else{
                    arrTurnOn[position] = true;
                    imgIconTypeSetting.setImageResource(arrImgIconTypeSettings[position]);

                    if(position == 0) {
                        turnOnSound();
                    } else {
                        turnOnVibrator();
                    }
                }
            }
        });

        return (item);
    }

    //Turn off sound
    private void turnOffSound() {
        AudioManager amanager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        amanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_SHOW_UI);
    }

    //Turn on sound
    public void turnOnSound() {

        AudioManager amanager = (AudioManager) context.getSystemService(AUDIO_SERVICE);
        amanager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_SHOW_UI);
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
