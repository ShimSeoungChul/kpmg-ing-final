package com.kpmg.kpmgclient.common;

import android.app.Activity;
import android.os.Build;

import androidx.core.content.ContextCompat;

import com.kpmg.kpmgclient.R;

public class StatusUtil {
    public enum StatusBarColorType {
        WHITE_STATUS_BAR( R.color.white ),
//        GREY_STATUS_BAR( R.color.grey_color_01 ),
        MAIN_STATUS_BAR( R.color.main );

        private int backgroundColorId;

        StatusBarColorType(int backgroundColorId){
            this.backgroundColorId = backgroundColorId;
        }

        public int getBackgroundColorId() {
            return backgroundColorId;
        }
    }

    public static void setStatusBarColor(Activity activity, StatusBarColorType colorType) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, colorType.getBackgroundColorId()));
        }
    }
}