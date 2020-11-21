package hcmus.nhom21.demoparchessi;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static java.lang.String.valueOf;

public class SettingFragment extends Fragment{
    // this fragment shows a ListView
    RunningGameActivity main;
    Context context = null;
    String message = "";

    private TextView txtMsg;
    private ListView listView;
    private Button btnExit;

    private String [] arrNameTypeSetting = {"Âm thanh", "Rung", "Thông báo"};
    private Integer [] arrImgIconTypeSettingOff = {R.drawable.ic_baseline_volume_off_24, R.drawable.ic_baseline_volume_off_24, R.drawable.ic_baseline_notifications_off_24};
    private Integer [] arrImgIconTypeSetting = {R.drawable.ic_baseline_volume_up_24, R.drawable.ic_baseline_phonelink_ring_24, R.drawable.ic_baseline_notifications_24};
    private int nowIndex=-1;
    public Parcelable state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            context = getActivity(); // use this reference to invoke main callbacks
            main = (RunningGameActivity) getActivity();
        }
        catch (IllegalStateException e) {
            throw new IllegalStateException("MainActivity must implement callbacks");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //View layout = (View) inflater.inflate(R.layout.fragment_setting, null);

        //Custom list view
        View view = (View) inflater.inflate(R.layout.fragment_listview_setting, null);
        listView = (ListView) view.findViewById(R.id.lstTypeSetting);
        final CustomTypeSettingAdapter adapter = new CustomTypeSettingAdapter(context, R.layout.item_listview_setting, arrImgIconTypeSetting, arrNameTypeSetting);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //.getCheckedItemPosition().setImageResource(arrImgIconTypeSettingsOff[position]);
                //listView.getChildAt(listView.getCheckedItemPosition()).setBackground;
                Toast.makeText(main, valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        btnExit = (Button) view.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("fragment", "btnExit is pressed");

                //Hiển thị lại
                main.findViewById(R.id.txtProfile).setVisibility(View.VISIBLE);
                main.findViewById(R.id.imgBoard).setVisibility(View.VISIBLE);
                main.findViewById(R.id.btnSetting).setVisibility(View.VISIBLE);

                //Xóa fragment
                //main.getSupportFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.frameSetting)).commit();
                main.getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }// onCreateView

    //    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        btnExit = (Button) getView().findViewById(R.id.btnExit);
//        btnExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("fragment", "btnExit is pressed");
//            }
//        });
//    }
}// class
