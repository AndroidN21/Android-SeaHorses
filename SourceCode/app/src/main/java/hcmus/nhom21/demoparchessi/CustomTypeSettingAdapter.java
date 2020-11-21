package hcmus.nhom21.demoparchessi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomTypeSettingAdapter extends ArrayAdapter<String> {
    Context context;
    String[] arrNameTypeSettings;
    Integer[] arrImgIconTypeSettings;

    public CustomTypeSettingAdapter( Context context, int layoutToBeInflated, Integer[] arrImgIconTypeSetting, String[] arrNameTypeSetting) {
        super(context, R.layout.item_listview_setting, arrNameTypeSetting);
        this.context = context;
        this.arrImgIconTypeSettings = arrImgIconTypeSetting;
        this.arrNameTypeSettings = arrNameTypeSetting;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View row = inflater.inflate(R.layout.item_listview_setting, null);
        TextView txtNameTypeSetting = (TextView) row.findViewById(R.id.txtNameTypeSetting);
        ImageView imgIconTypeSetting = (ImageView) row.findViewById(R.id.imgIconTypeSetting);

        imgIconTypeSetting.setImageResource(arrImgIconTypeSettings[position]);
        txtNameTypeSetting.setText(arrNameTypeSettings[position]);
        return (row);
    }
}
