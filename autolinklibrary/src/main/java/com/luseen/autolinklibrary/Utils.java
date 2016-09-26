package com.luseen.autolinklibrary;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Property;

/**
 * Created by Chatikyan on 25.09.2016-18:57.
 */

public class Utils {

    static final Property<MutableForegroundColorSpan, Integer> MUTABLE_FOREGROUND_COLOR_SPAN_PROPERTY =
            new Property<MutableForegroundColorSpan, Integer>(Integer.class, "MUTABLE_FOREGROUND_COLOR_SPAN_FC_PROPERTY") {

                @Override
                public void set(MutableForegroundColorSpan span, Integer value) {
                    span.setForegroundColor(value);
                }

                @Override
                public Integer get(MutableForegroundColorSpan span) {
                    return span.getForegroundColor();
                }
            };
}
