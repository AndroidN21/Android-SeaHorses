package hcmus.nhom21.parcheesigame;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;


public class MusicThread {
    static private MediaPlayer mediaPlayer;
    static private String filename;
    static private Context context;
    static private MusicThread musicThread;
    static int volume;

    private MusicThread(Context context, String filename) {
        this.filename = filename;
        this.context = context;
        Uri uri = GetUri(filename);
        mediaPlayer = MediaPlayer.create(context, uri);
        volume = 50;
    }

    static public MusicThread GetMusicThread(Context context, String filename) {
        if (musicThread == null) {
            musicThread = new MusicThread(context, filename);
        }
        return musicThread;
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

    static public void Stop() {
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
    }

    static private Uri GetUri(String filename) {
        int resID = context.getResources().getIdentifier(filename, "raw", "hcmus.nhom21.parcheesigame");
        return Uri.parse("android.resource://hcmus.nhom21.parcheesigame/" + resID);
    }

    static public void PlaySong(String filename, Context context) {
        MusicThread.context = context;
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();

            Uri uri = GetUri(filename);
            mediaPlayer = MediaPlayer.create(context, uri);
            SetVolume(volume);
            mediaPlayer.start();
        }else {
            volume = 50;

            Uri uri = GetUri(filename);
            mediaPlayer = MediaPlayer.create(context, uri);
            SetVolume(volume);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }
    }
}
