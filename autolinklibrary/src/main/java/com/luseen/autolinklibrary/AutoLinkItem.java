package com.luseen.autolinklibrary;

/**
 * Created by Chatikyan on 25.09.2016-19:43.
 */

class AutoLinkItem {

    private AutoLinkMode autoLinkMode;

    private String matchedText;

    private int startPoint,endPoint;

    AutoLinkItem(int startPoint, int endPoint, String matchedText, AutoLinkMode autoLinkMode) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.matchedText = matchedText;
        this.autoLinkMode = autoLinkMode;
    }

    AutoLinkMode getAutoLinkMode() {
        return autoLinkMode;
    }

    String getMatchedText() {
        return matchedText;
    }

    int getStartPoint() {
        return startPoint;
    }

    int getEndPoint() {
        return endPoint;
    }
}
