package hcmus.nhom21.parcheesigame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class CopyRightInfoActivity extends Activity {
    private TextView txtVecteezy;
    private TextView txtFreepik;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyright_info);
        txtVecteezy = (TextView) findViewById(R.id.txtVecteezy);
        txtFreepik = (TextView) findViewById(R.id.txtFreepik);

        txtVecteezy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showWebDialogBox(R.drawable.ic_vecteezy_logo, R.string.intro0_vecteezy, R.string.intro1_vecteezy, "https://www.vecteezy.com");
                SFXThread.PlaySound("sfx_button",getApplicationContext());
            }
        });

        txtFreepik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                showWebDialogBox(R.drawable.logo_freepik, R.string.intro0_freepik, R.string.intro1_freepik, "https://www.freepik.com");
            }
        });
    }

    private void showWebDialogBox(int logo, int intro0, int intro1, final String link) {
        final Dialog customDialog = new Dialog(this);
        customDialog.setContentView(R.layout.custom_dialog_layout);
        ImageView imgLogo = (ImageView) customDialog.findViewById(R.id.imgLogo);
        TextView txtIntro0 = (TextView) customDialog.findViewById(R.id.txtIntro0);
        TextView txtIntro1 = (TextView) customDialog.findViewById(R.id.txtIntro1);
        Button btnMore = (Button) customDialog.findViewById(R.id.btnMore);
        Button btnClose = (Button) customDialog.findViewById(R.id.btnClose);

        imgLogo.setImageResource(logo);
        txtIntro0.setText(intro0);
        txtIntro1.setText(intro1);

        btnMore.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);
                customDialog.dismiss();
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SFXThread.PlaySound("sfx_button",getApplicationContext());
                customDialog.dismiss();
            }
        });

        customDialog.show();
    }
}
