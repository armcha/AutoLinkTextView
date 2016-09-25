package com.luseen.activetextview;

/**
 * Created by Chatikyan on 25.09.2016-19:43.
 */

public class AutoLinkItem {

    private Integer startPoint;

    private Integer endPoint;

    private String matchedText;

    public AutoLinkItem(Integer startPoint, Integer endPoint, String matchedText) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.matchedText = matchedText;
    }

    public String getMatchedText() {
        return matchedText;
    }

    public void setMatchedText(String matchedText) {
        this.matchedText = matchedText;
    }

    public Integer getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Integer startPoint) {
        this.startPoint = startPoint;
    }

    public Integer getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Integer endPoint) {
        this.endPoint = endPoint;
    }
}
