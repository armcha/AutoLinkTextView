package com.luseen.activetextview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoLinkTextView autoLinkTextView = (AutoLinkTextView) findViewById(R.id.active);

        //autoLinkTextView.enableUnderLine();

        autoLinkTextView.addAutoLinkMode(
                AutoLinkMode.MODE_HASHTAG,
                AutoLinkMode.MODE_PHONE,
                AutoLinkMode.MODE_URL,
                AutoLinkMode.MODE_EMAIL,
                AutoLinkMode.MODE_MENTION);

        //autoLinkTextView.setCustomRegex("\\sAllo\\b");

        autoLinkTextView.setHashtagModeColor(ContextCompat.getColor(this, R.color.color2));
        autoLinkTextView.setPhoneModeColor(ContextCompat.getColor(this, R.color.color3));
        autoLinkTextView.setCustomModeColor(ContextCompat.getColor(this, R.color.color1));
        autoLinkTextView.setMentionModeColor(ContextCompat.getColor(this, R.color.color5));

        autoLinkTextView.setText(getString(R.string.long_text));

        autoLinkTextView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(AutoLinkMode autoLinkMode, String matchedText) {
                showDialog(autoLinkMode.toString(), matchedText);
            }
        });
    }

    private void showDialog(String title, String message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
