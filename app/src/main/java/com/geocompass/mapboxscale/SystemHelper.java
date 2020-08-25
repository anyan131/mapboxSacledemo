package com.geocompass.mapboxscale;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by gxsn on 2018/5/16.
 */

public class SystemHelper {
    
    /**
     * 像素转密度Dpi
     *
     * @param context
     * @param px
     *            像素值
     * @return 密度Dpi值
     */
    public static int px2dip(Context context, float px) {
        return (int) (0.5F + px / getDensity(context));
    }
    
    /**
     * 密度Dpi转像素
     *
     * @param context
     * @param dip
     *            密度Dpi值
     * @return 像素值
     */
    public static int dip2px(Context context, float dip) {
        return (int) (0.5F + dip * getDensity(context));
    }
    
    /**
     * 将px值转换为sp值
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    
    /**
     * 将sp值转换为px值
     *
     * @param context
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
    
    public static DisplayMetrics getScreenInfo(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(dm);
        // int w = dm.widthPixels;//寬度（像素）
        // int h = dm.heightPixels; //高度（像素）
        // float d = dm.density; //密度（0.75 / 1.0 / 1.5）
        // int densityDpi = dm.densityDpi; // 屏幕密度DPI（120 / 160 / 240）
        return dm;
    }
    
    /**
     * 获取屏幕密度,如0.75/1.0/1.5
     *
     * @return 密度float值
     */
    public static float getDensity(Context context) {
        DisplayMetrics dm = getScreenInfo(context);
        return dm.density;
    }
}
