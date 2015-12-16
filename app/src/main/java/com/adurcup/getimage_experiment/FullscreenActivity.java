package com.adurcup.getimage_experiment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    /**
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 1000 * 2;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */

     private final Handler mHideHandler=new Handler();
     private final Runnable mHideRunnable=new Runnable() {
        @Override
        public void run() {
            finish();
            startActivity(new Intent(FullscreenActivity.this,WhichId.class));
        }
     };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        AutoStartActivity(AUTO_HIDE_DELAY_MILLIS);
    }
    private void AutoStartActivity(int autoHideDelayMillis) {
        mHideHandler.postDelayed(mHideRunnable, autoHideDelayMillis);
    }
}
