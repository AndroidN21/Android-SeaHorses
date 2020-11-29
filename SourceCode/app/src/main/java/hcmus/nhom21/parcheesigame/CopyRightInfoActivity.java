package hcmus.nhom21.parcheesigame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CopyRightInfoActivity extends Activity {
    private Button btnBack;
    private TextView txtTitle;
    private TextView txtLine0;
    private TextView txtLine1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyright_info);
        btnBack = (Button) findViewById(R.id.btnBack);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtLine0 = (TextView) findViewById(R.id.txtLine0);
        txtLine1 = (TextView) findViewById(R.id.txtLine1);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtTitle.setText(R.string.title_copyright_info);
        txtLine0.setText(R.string.info0);
        txtLine1.setText(R.string.info1);
    }
}
