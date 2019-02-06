package com.wsoteam.diet.Appodeal;

import android.util.Log;

import com.wsoteam.diet.Config;

public class ADsSingleton {

    private static ADsSingleton instance;
    private static String TAG = "appodeal";

    private int AD_index = Config.INTERSTITIAL_FREQUENCY;

    private ADsSingleton(){ }

    public static void initInstance() {
        Log.i(TAG, "MySingleton::InitInstance()");
        if (instance == null) {
            instance = new ADsSingleton();
        }
    }

    public static ADsSingleton getInstance() {
        Log.i(TAG, "MySingleton::getInstance()");
        return instance;
    }

    public boolean check(boolean isLoadet){
        if (AD_index > 0){
            Log.i(TAG, "MySingleton::check - > 0");
            AD_index--;
            return false;
        } else if (AD_index == 0 && isLoadet){
            Log.i(TAG, "MySingleton::check - == 0");
            AD_index = Config.INTERSTITIAL_FREQUENCY;
            return true;
        } else {
            Log.i(TAG, "MySingleton::check - another");
            return false;
        }
    }
}
