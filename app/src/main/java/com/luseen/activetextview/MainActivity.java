package com.luseen.activetextview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.luseen.autolinklibrary.AutoLinkMode;
import com.luseen.autolinklibrary.AutoLinkOnClickListener;
import com.luseen.autolinklibrary.AutoLinkTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoLinkTextView textView = (AutoLinkTextView) findViewById(R.id.active);

        textView.setAutoLinkMode(
                AutoLinkMode.MODE_EMAIL);

        textView.setAutoLinkText("Lorem Ipsum is #simply dummy text of the printing and #typesetting industry. " +
                        "Lorem 093 02 30 17 has been @since the 1500s #the @industry's standard dummy text.com ever @since the 1500s, when an unknown " +
                        "printer took a galley 093023017 of type and scrambled it to https://popularised.am make a type @specimen book. It has survived not " +
                        "only five @centuries, but.ru also chatikyana@mail.ru the leap into 093-02-30-17 electronic typesetting, remaining093023017essentially unchanged. " +
                        "It was  https://github.com in the 1960s with the release of #Letraset sheets containing Lorem Ipsum #passages," +
                        " and more recently with desktop 010365478 @publishing software like Aldus @PageMaker including versions of #Lorem Ipsum." + "Lorem Ipsum is #simply (077) 08 1578 dummy text of the printing and #typesetting industry. " +
                        "Lorem @Ipsum has been #the industry's standard dummy text ever since the 1500s, when an unknown " +
                        "printer took a galley of +37493023017 type and scrambled it to make a type specimen book. It has survived not ",
                Color.RED);

        textView.setAutoLinkOnClickListener(new AutoLinkOnClickListener() {
            @Override
            public void onAutoLinkTextClick(String matchedText) {
                Log.e("onAutoLinkTextClick ", "" + matchedText);
            }
        });
    }
}
