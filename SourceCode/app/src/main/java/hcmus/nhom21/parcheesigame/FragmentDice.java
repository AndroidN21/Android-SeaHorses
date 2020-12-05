package hcmus.nhom21.parcheesigame;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class FragmentDice extends Fragment {
    private RunningGameActivity superActivity;
    private Context context = null;
    private ImageView imgDice1;
    private ImageView imgDice2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout layoutRollDice = (LinearLayout) inflater.inflate(R.layout.fragment_roll_dice,null);
        imgDice1 = layoutRollDice.findViewById(R.id.img_dice1);
        imgDice2 = layoutRollDice.findViewById(R.id.img_dice2);
        imgDice1.setImageResource(R.drawable.ic_dice_7);
        imgDice2.setImageResource(R.drawable.ic_dice_1);
        return layoutRollDice;
    }
    public void setImgDice1(int res){
        switch (res){
            case 1: imgDice1.setImageResource(R.drawable.ic_dice_1); break;
            case 2: imgDice1.setImageResource(R.drawable.ic_dice_2); break;
            case 3: imgDice1.setImageResource(R.drawable.ic_dice_3); break;
            case 4: imgDice1.setImageResource(R.drawable.ic_dice_4); break;
            case 5: imgDice1.setImageResource(R.drawable.ic_dice_5); break;
            case 6: imgDice1.setImageResource(R.drawable.ic_dice_6); break;
            case 7: imgDice1.setImageResource(R.drawable.ic_dice_7); break;
            case 8: imgDice1.setImageResource(R.drawable.ic_dice_8); break;
            case 9: imgDice1.setImageResource(R.drawable.ic_dice_9); break;
            case 10: imgDice1.setImageResource(R.drawable.ic_dice_10); break;
            case 11: imgDice1.setImageResource(R.drawable.ic_dice_11); break;
            case 12: imgDice1.setImageResource(R.drawable.ic_dice_12); break;
            case 13: imgDice1.setImageResource(R.drawable.ic_dice_13); break;
            case 14: imgDice1.setImageResource(R.drawable.ic_dice_14); break;
            case 15: imgDice1.setImageResource(R.drawable.ic_dice_15); break;
            case 16: imgDice1.setImageResource(R.drawable.ic_dice_16); break;
            case 17: imgDice1.setImageResource(R.drawable.ic_dice_17); break;
            case 18: imgDice1.setImageResource(R.drawable.ic_dice_18); break;
        }
    }
    public void setImgDice2(int res){
        switch (res){
            case 1: imgDice2.setImageResource(R.drawable.ic_dice_1); break;
            case 2: imgDice2.setImageResource(R.drawable.ic_dice_2); break;
            case 3: imgDice2.setImageResource(R.drawable.ic_dice_3); break;
            case 4: imgDice2.setImageResource(R.drawable.ic_dice_4); break;
            case 5: imgDice2.setImageResource(R.drawable.ic_dice_5); break;
            case 6: imgDice2.setImageResource(R.drawable.ic_dice_6); break;
            case 7: imgDice2.setImageResource(R.drawable.ic_dice_7); break;
            case 8: imgDice2.setImageResource(R.drawable.ic_dice_8); break;
            case 9: imgDice2.setImageResource(R.drawable.ic_dice_9); break;
            case 10: imgDice2.setImageResource(R.drawable.ic_dice_10); break;
            case 11: imgDice2.setImageResource(R.drawable.ic_dice_11); break;
            case 12: imgDice2.setImageResource(R.drawable.ic_dice_12); break;
            case 13: imgDice2.setImageResource(R.drawable.ic_dice_13); break;
            case 14: imgDice2.setImageResource(R.drawable.ic_dice_14); break;
            case 15: imgDice2.setImageResource(R.drawable.ic_dice_15); break;
            case 16: imgDice2.setImageResource(R.drawable.ic_dice_16); break;
            case 17: imgDice2.setImageResource(R.drawable.ic_dice_17); break;
            case 18: imgDice2.setImageResource(R.drawable.ic_dice_18); break;
        }
    }
}
