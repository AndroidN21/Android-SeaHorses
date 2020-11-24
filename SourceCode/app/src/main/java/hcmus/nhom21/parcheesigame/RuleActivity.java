package hcmus.nhom21.parcheesigame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RuleActivity extends Activity {
    private Button btnBack;
    private TextView txtTitle;
    private TextView txtLine0;
    private TextView txtLine1;
    private TextView txtLine2;
    private TextView txtLine3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);
        btnBack = (Button) findViewById(R.id.btnBack);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtLine0 = (TextView) findViewById(R.id.txtLine0);
        txtLine1 = (TextView) findViewById(R.id.txtLine1);
        txtLine2 = (TextView) findViewById(R.id.txtLine2);
        txtLine3 = (TextView) findViewById(R.id.txtLine3);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtTitle.setText(R.string.title_rule);
        txtLine0.setText(R.string.rule0_ra_quan);
        txtLine1.setText(R.string.rule1_chien_thang);
        txtLine2.setText(R.string.rule2_da);
        txtLine3.setText(R.string.rule3_vao_chuong);
    }
}
