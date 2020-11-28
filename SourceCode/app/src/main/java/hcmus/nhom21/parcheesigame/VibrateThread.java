package hcmus.nhom21.parcheesigame;

import android.content.Context;
import android.os.Vibrator;

public class VibrateThread {
    static private Vibrator vibrator;
    static private Context context;
    static private VibrateThread vibrateThread;
    static private int time;
    static private boolean state;

    private VibrateThread() {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        time = 500;
    }

    static private VibrateThread GetVibrateThread() {
        if (vibrateThread == null) {
            vibrateThread = new VibrateThread();
        }
        return vibrateThread;
    }

    static public void SetTime(int time) {
        VibrateThread.time = time;
    }

    static public void On() {
        state = true;
    }

    static public void Off() {
        state = false;
    }

    static public boolean State(){
        return state;
    }


    static public void run(Context context) {
        if (!state){
            return;
        }
        VibrateThread.context = context;
        if (vibrator == null) {
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            time = 500;
        }
        vibrator.vibrate(time); // for 500 ms
    }
}
