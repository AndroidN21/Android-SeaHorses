package hcmus.nhom21.parcheesigame;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;


public class MusicThread {
    static private MediaPlayer mediaPlayer;
    static private String filename;
    static private Context context;
    static private MusicThread musicThread;

    private MusicThread(Context context, String filename) {
        this.filename = filename;
        this.context = context;
        Uri uri = GetUri(filename);
        mediaPlayer = MediaPlayer.create(context,uri);

    }

    static public MusicThread GetMusicThread(Context context, String filename) {
        if (musicThread == null) {
            musicThread = new MusicThread(context, filename);
        }
        return musicThread;
    }

    static private Uri GetUri(String filename) {
        int resID = context.getResources().getIdentifier(filename,"raw","hcmus.nhom21.parcheesigame");
        return Uri.parse("android.resource://hcmus.nhom21.parcheesigame/"+ resID);
    }

    static public void PlaySong(String filename,Context context) {
        MusicThread.context=context;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
        }
        Uri uri = GetUri(filename);
        mediaPlayer = MediaPlayer.create(context,uri);
        mediaPlayer.start();
    }
}

//package hcmus.nhom21.parcheesigame;
//
//import android.app.Activity;
//import android.app.Notification;
//import android.content.Context;
//import android.content.UriMatcher;
//import android.media.MediaPlayer;
//import android.net.Uri;
//
//public class MusicThread implements Runnable{
//    static private MediaPlayer mediaPlayer;
//    static private String filename;
//    static private Context context;
//    static private MusicThread musicThread;
//
//    private MusicThread(Context context, String filename){
//        this.filename = filename;
//        this.context = context;
//    }
//
//    public MusicThread GetMusicThread(Context context, String filename){
//        if(musicThread==null){
//            musicThread = new MusicThread(context, filename);
//        }
//        return musicThread;
//    }
//
//    static public void ChangeSong(String filename){
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
