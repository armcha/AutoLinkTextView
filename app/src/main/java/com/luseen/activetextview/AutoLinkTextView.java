package com.luseen.activetextview;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chatikyan on 25.09.2016-18:53.
 */

public class AutoLinkTextView extends TextView {

    private final int MIN_PHONE_NUMBER_LENGTH = 8;

    private AutoLinkOnClickListener autoLinkOnClickListener;

    private AutoLinkMode[] autoLinkModes;

    public AutoLinkTextView(Context context) {
        super(context);
    }

    public AutoLinkTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setHighlightColor(int color) {
        super.setHighlightColor(Color.TRANSPARENT);
    }

    public void setAutoLinkText(String text, int autoLinkTextColor) {
        long startTime = System.currentTimeMillis();

        SpannableString spannableString = makeSpannableString(text);
        setText(spannableString);
        setMovementMethod(LinkMovementMethod.getInstance());
        setLinkTextColor(autoLinkTextColor);

        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        Log.e("setAutoLinkText ", duration + " milli second");
    }

    private SpannableString makeSpannableString(String text) {

        SpannableString spannableString = new SpannableString(text);

        List<AutoLinkItem> autoLinkItems = matchedRanges(text);

        for (final AutoLinkItem autoLinkItem : autoLinkItems) {

            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    if (autoLinkOnClickListener != null)
                        autoLinkOnClickListener.onAutoLinkTextClick(autoLinkItem.getMatchedText());
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };

            spannableString.setSpan(
                    clickableSpan,
                    autoLinkItem.getStartPoint(),
                    autoLinkItem.getEndPoint(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        return spannableString;
    }

    private List<AutoLinkItem> matchedRanges(String text) {

        List<AutoLinkItem> autoLinkItems = new ArrayList<>();
        for (AutoLinkMode anAutoLinkMode : autoLinkModes) {
            String regex = getRegexByAutoLinkMode(anAutoLinkMode);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            if (anAutoLinkMode == AutoLinkMode.MODE_PHONE) {
                while (matcher.find()) {
                    if (matcher.group().length() > MIN_PHONE_NUMBER_LENGTH)
                        autoLinkItems.add(new AutoLinkItem(matcher.start(), matcher.end(), matcher.group()));
                }
            } else {
                while (matcher.find()) {
                    autoLinkItems.add(new AutoLinkItem(matcher.start(), matcher.end(), matcher.group()));
                }
            }
        }

        return autoLinkItems;
    }

    private String getRegexByAutoLinkMode(AutoLinkMode anAutoLinkMode) {
        switch (anAutoLinkMode) {
            case MODE_HASHTAG:
                return RegexParser.HASHTAG_PATTERN;
            case MODE_MENTION:
                return RegexParser.MENTION_PATTERN;
            case MODE_URL:
                return RegexParser.URL_PATTERN;
            case MODE_PHONE:
                return RegexParser.PHONE_PATTERN;
            case MODE_EMAIL:
                return RegexParser.EMAIL_PATTERN;
            default:
                return RegexParser.URL_PATTERN;
        }
    }

    public void setAutoLinkMode(AutoLinkMode... autoLinkModes) {
        this.autoLinkModes = autoLinkModes;
    }

    public void setAutoLinkOnClickListener(AutoLinkOnClickListener autoLinkOnClickListener) {
        this.autoLinkOnClickListener = autoLinkOnClickListener;
    }
}
