package xyz.bzennn.vkmusicactualizer.utils;

import android.util.Log;

public final class Utils {
    public static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
