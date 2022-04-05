package com.example.golfskinsapp;

import android.media.metrics.Event;

import java.util.List;
import java.util.Map;

public interface SRCallBack {
    void onCallBack(String documentID, Map<String, Object> data);
}
