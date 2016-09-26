package com.luseen.autolinklibrary;

import android.graphics.Color;
import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;

/**
 * Created by Chatikyan on 26.09.2016-14:37.
 */
class MutableForegroundColorSpan extends ForegroundColorSpan {

    private int foregroundColor;

    MutableForegroundColorSpan(int color) {
        super(color);
        foregroundColor = color;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(foregroundColor);
    }

    void setForegroundColor(int foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setColor(getForegroundColor());
    }

    @Override
    public int getForegroundColor() {
        return Color.rgb(Color.red(foregroundColor), Color.green(foregroundColor), Color.blue(foregroundColor));
    }
}
