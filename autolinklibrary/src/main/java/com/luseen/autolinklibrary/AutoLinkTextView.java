package com.luseen.autolinklibrary;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.text.DynamicLayout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Chatikyan on 25.09.2016-18:53.
 */

public class AutoLinkTextView extends TextView {

    static final String TAG = AutoLinkTextView.class.getSimpleName();

    private static final int MIN_PHONE_NUMBER_LENGTH = 8;

    private static final int DEFAULT_COLOR = Color.RED;

    private AutoLinkOnClickListener autoLinkOnClickListener;

    private AutoLinkMode[] autoLinkModes;
    private List<AutoLinkMode> mBoldAutoLinkModes;

    private String customRegex;

    private boolean isUnderLineEnabled = false;

    private int mentionModeColor = DEFAULT_COLOR;
    private int hashtagModeColor = DEFAULT_COLOR;
    private int urlModeColor = DEFAULT_COLOR;
    private int phoneModeColor = DEFAULT_COLOR;
    private int emailModeColor = DEFAULT_COLOR;
    private int customModeColor = DEFAULT_COLOR;
    private int defaultSelectedColor = Color.LTGRAY;

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

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (TextUtils.isEmpty(text)) {
            super.setText(text, type);
            return;
        }
        SpannableString spannableString = makeSpannableString(text);
        setMovementMethod(new LinkTouchMovementMethod());
        super.setText(spannableString, type);
    }

    private SpannableString makeSpannableString(CharSequence text) {

        final SpannableString spannableString = new SpannableString(text);

        List<AutoLinkItem> autoLinkItems = matchedRanges(text);

        for (final AutoLinkItem autoLinkItem : autoLinkItems) {
            int currentColor = getColorByMode(autoLinkItem.getAutoLinkMode());

            TouchableSpan clickableSpan = new TouchableSpan(currentColor, defaultSelectedColor, isUnderLineEnabled) {
                @Override
                public void onClick(View widget) {
                    if (autoLinkOnClickListener != null)
                        autoLinkOnClickListener.onAutoLinkTextClick(
                                autoLinkItem.getAutoLinkMode(),
                                autoLinkItem.getMatchedText());
                }
            };

            spannableString.setSpan(
                    clickableSpan,
                    autoLinkItem.getStartPoint(),
                    autoLinkItem.getEndPoint(),
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // check if we should make this auto link item bold
            if(mBoldAutoLinkModes != null && mBoldAutoLinkModes.contains(autoLinkItem.getAutoLinkMode())){

                // make the auto link item bold
                spannableString.setSpan(
                        new StyleSpan(Typeface.BOLD),
                        autoLinkItem.getStartPoint(),
                        autoLinkItem.getEndPoint(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }

        }

        return spannableString;
    }

    private List<AutoLinkItem> matchedRanges(CharSequence text) {

        List<AutoLinkItem> autoLinkItems = new LinkedList<>();

        if (autoLinkModes == null) {
            throw new NullPointerException("Please add at least one mode");
        }

        for (AutoLinkMode anAutoLinkMode : autoLinkModes) {
            String regex = Utils.getRegexByAutoLinkMode(anAutoLinkMode, customRegex);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            if (anAutoLinkMode == AutoLinkMode.MODE_PHONE) {
                while (matcher.find()) {
                    if (matcher.group().length() > MIN_PHONE_NUMBER_LENGTH)
                        autoLinkItems.add(new AutoLinkItem(
                                matcher.start(),
                                matcher.end(),
                                matcher.group(),
                                anAutoLinkMode));
                }
            } else {
                while (matcher.find()) {
                    autoLinkItems.add(new AutoLinkItem(
                            matcher.start(),
                            matcher.end(),
                            matcher.group(),
                            anAutoLinkMode));
                }
            }
        }

        return autoLinkItems;
    }

    private int getColorByMode(AutoLinkMode autoLinkMode) {
        switch (autoLinkMode) {
            case MODE_HASHTAG:
                return hashtagModeColor;
            case MODE_MENTION:
                return mentionModeColor;
            case MODE_URL:
                return urlModeColor;
            case MODE_PHONE:
                return phoneModeColor;
            case MODE_EMAIL:
                return emailModeColor;
            case MODE_CUSTOM:
                return customModeColor;
            default:
                return DEFAULT_COLOR;
        }
    }

    public void setMentionModeColor(@ColorInt int mentionModeColor) {
        this.mentionModeColor = mentionModeColor;
    }

    public void setHashtagModeColor(@ColorInt int hashtagModeColor) {
        this.hashtagModeColor = hashtagModeColor;
    }

    public void setUrlModeColor(@ColorInt int urlModeColor) {
        this.urlModeColor = urlModeColor;
    }

    public void setPhoneModeColor(@ColorInt int phoneModeColor) {
        this.phoneModeColor = phoneModeColor;
    }

    public void setEmailModeColor(@ColorInt int emailModeColor) {
        this.emailModeColor = emailModeColor;
    }

    public void setCustomModeColor(@ColorInt int customModeColor) {
        this.customModeColor = customModeColor;
    }

    public void setSelectedStateColor(@ColorInt int defaultSelectedColor) {
        this.defaultSelectedColor = defaultSelectedColor;
    }

    public void addAutoLinkMode(AutoLinkMode... autoLinkModes) {
        this.autoLinkModes = autoLinkModes;
    }

    public void setBoldAutoLinkModes(AutoLinkMode... autoLinkModes) {
        mBoldAutoLinkModes = new ArrayList<>();
        mBoldAutoLinkModes.addAll(Arrays.asList(autoLinkModes));
    }

    public void setCustomRegex(String regex) {
        this.customRegex = regex;
    }

    public void setAutoLinkOnClickListener(AutoLinkOnClickListener autoLinkOnClickListener) {
        this.autoLinkOnClickListener = autoLinkOnClickListener;
    }


    /**
     * fix ellipsize not work bug
     * https://stackoverflow.com/questions/14691511/textview-using-spannable-ellipsize-doesnt-work
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Build.VERSION.SDK_INT >= 16) {
            StaticLayout layout = null;
            Field field = null;
            try {
                Field staticField = DynamicLayout.class.getDeclaredField("sStaticLayout");
                staticField.setAccessible(true);
                layout = (StaticLayout) staticField.get(DynamicLayout.class);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (layout != null) {
                try {
                    field = StaticLayout.class.getDeclaredField("mMaximumVisibleLineCount");
                    field.setAccessible(true);
                    field.setInt(layout, getMaxLines());
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            if (layout != null && field != null) {
                try {
                    field.setInt(layout, Integer.MAX_VALUE);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void enableUnderLine() {
        isUnderLineEnabled = true;
    }

}
