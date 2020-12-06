package hcmus.nhom21.parcheesigame;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class SFXThread {
    static private MediaPlayer mediaPlayer;
    static private String filename;
    static private Context context;
    static private SFXThread sfxThread;
    static int volume;

    private SFXThread(Context context, String filename) {
        SFXThread.filename = filename;
        SFXThread.context = context;
        Uri uri = GetUri2(filename);
        mediaPlayer = MediaPlayer.create(context, uri);
        volume = 50;
    }

    public SFXThread GetSFXThread(Context context, String filename) {
        if (sfxThread == null) {
            sfxThread = new SFXThread(context, filename);
        }
        return sfxThread;
    }

    static private Uri GetUri2(String filename) {
        int resID = context.getResources().getIdentifier(filename, "raw", "hcmus.nhom21.parcheesigame");
        return Uri.parse("android.resource://hcmus.nhom21.parcheesigame/" + resID);
    }

    static public void Stop() {
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    static public void SetVolume(int k) {
        volume = k;
        if (mediaPlayer == null) {
            return;
        }
        float v = (float) volume / 100;
        mediaPlayer.setVolume(v, v);
    }

    static public int GetVolume() {
        return volume;
    }

    static public void PlaySound(String filename, Context context) {
        SFXThread.context = context;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        } else {
            volume = 50;
        }

        Uri uri = GetUri2(filename);
        mediaPlayer = MediaPlayer.create(context, uri);
        SetVolume(volume);
        mediaPlayer.start();
    }
}


//package hcmus.nhom21.parcheesigame;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.net.Uri;
//
//public class SFXThread implements Runnable {
//    static private MediaPlayer mediaPlayer;
//    static private String filename;
//    static private Context context;
//    static private SFXThread sfxThread;
//
//    private SFXThread(Context context, String filename){
//        this.filename = filename;
//        this.context = context;
//    }
//
//    public SFXThread GetSFXThread(Context context, String filename){
//        if(sfxThread ==null){
//            sfxThread = new SFXThread(context, filename);
//        }
//        return sfxThread;
//    }
//
//    static public void PlaySound(String filename){
//        if (mediaPlayer.isPlaying()) {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//            Uri uri = Uri.parse("R.raw."+filename);
//            mediaPlayer = MediaPlayer.create(context, uri);
//        }
//        mediaPlayer.start();
//    }
//
//    @Override
//    public void run() {
//        Uri uri = Uri.parse("R.raw."+filename);
//        mediaPlayer = MediaPlayer.create(context,uri);
//        mediaPlayer.start();
//    }
//}