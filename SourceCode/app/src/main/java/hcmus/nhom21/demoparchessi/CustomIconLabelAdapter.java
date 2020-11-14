package hcmus.nhom21.demoparchessi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomIconLabelAdapter extends ArrayAdapter<String> {
    Context context;
    Integer[] arrImgAvts;
    String[] arrIDs;
    public int posSelected = 0;
    public CustomIconLabelAdapter(Context context, int layoutToBeInflated, String[] arrID, Integer[] arrImgAvt) {
        super(context, R.layout.item_listview_setting, arrID);
        this.context = context;
        this.arrImgAvts = arrImgAvt;
        this.arrIDs = arrID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();

        View row = inflater.inflate(R.layout.item_listview_setting, null);

        TextView txtID = (TextView) row.findViewById(R.id.txtNameTypeSetting);
        ImageView icon = (ImageView) row.findViewById(R.id.imgIconTypeSetting);

        txtID.setText(arrIDs[position]);
        icon.setImageResource(arrImgAvts[position]);
        return (row);
    }
}
