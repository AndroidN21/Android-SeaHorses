package hcmus.nhom21.parcheesigame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;


public class RuleActivity extends Activity {
    Button btnExit;
    Button btn_GGvoice;
    private TextView txtTitle;
    private TextView txtLine0;
    private TextView txtLine1;
    private TextView txtLine2;
    private TextView txtLine3;
    private TextView txtLine4;
    private TextView txtLine5;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rule);

        btnExit = (Button) findViewById(R.id.btn_exit);
        btn_GGvoice = (Button) findViewById(R.id.btn_GGvoice);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtLine0 = (TextView) findViewById(R.id.txtRule_intro);
        txtLine1 = (TextView) findViewById(R.id.txtRule0);
        txtLine2 = (TextView) findViewById(R.id.txtRule1);
        txtLine3 = (TextView) findViewById(R.id.txtRule2);
        txtLine4 = (TextView) findViewById(R.id.txtRule3);
        txtLine5 = (TextView) findViewById(R.id.txtRule4);

        txtTitle.setText(R.string.title_rule);
        txtLine0.setText(R.string.rule_intro);
        txtLine1.setText(R.string.rule0_ra_quan);
        txtLine2.setText(R.string.rule1_bi_can);
        txtLine3.setText(R.string.rule2_da);
        txtLine4.setText(R.string.rule3_vao_chuong);
        txtLine5.setText(R.string.rule4_chien_thang);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                SFXThread.Stop();
                MusicThread.PlaySong("a1",getApplicationContext());
            }
        });

        btn_GGvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFXThread.PlaySound("google_voice",getApplicationContext());
                MusicThread.PlaySong("reviewphim",getApplicationContext());

            }
        });
    }
}

