package com.geocompass.mapboxscale;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.mapbox.mapboxsdk.maps.MapboxMap;

/**
 * Created by admin on 2018/8/8.
 */

public class MapScaleView extends View {
    private Paint mPaint;
    private int scaleWidth;
    private int scaleHeight;
    private int scaleSpaceText;
    private int textSize;
    private String text;
    private int marginStartX = 20;//距离x轴20px处开始绘制比例尺
    private int verticalY = 5;//竖线相对横线向上或向下的距离

    public MapScaleView(Context context) {
        this(context, null);
    }

    public MapScaleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    //这个构造方法是在第二个基础上再传入style的。
    public MapScaleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    private void initView() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        setBackgroundColor(getResources().getColor(R.color.transparent));
        scaleWidth = 360;//比例尺宽度
        scaleHeight = SystemHelper.dip2px(this.getContext(), 3);//比例尺高度
        text = "10km";//比例尺文本
        textSize = SystemHelper.sp2px(this.getContext(), 13);//比例尺字体
        scaleSpaceText = scaleHeight;//比例尺文本与图形的间隔高度
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(2f);//设置画笔宽度
        mPaint.setTextSize(textSize);
    }

    /**
     * 从距离x轴20，y轴画布宽的2/3处开始画线
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制直线
        canvas.drawLine(marginStartX, 2 * getHeight() / 3 - verticalY, marginStartX, 2 * getHeight() / 3 + verticalY, mPaint);//绘制比例尺左边竖线
        canvas.drawLine(marginStartX, 2 * getHeight() / 3, marginStartX + scaleWidth, 2 * getHeight() / 3, mPaint);//绘制比例尺可变横线
        canvas.drawLine(marginStartX + scaleWidth, 2 * getHeight() / 3 - verticalY,
                marginStartX + scaleWidth, 2 * getHeight() / 3 + verticalY, mPaint);//绘制比例尺右边竖线
        canvas.drawText(text, marginStartX, textSize, mPaint);//绘制字体
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int resultWidth;
        if (widthMode == MeasureSpec.EXACTLY) {
            resultWidth = widthSize;
        } else {
            resultWidth = scaleWidth;
            if (widthMode == MeasureSpec.AT_MOST) {
                resultWidth = Math.min(resultWidth, widthSize);
            }
        }
        int heightSize = getHeightSize(heightMeasureSpec) + 20;
        setMeasuredDimension(resultWidth, heightSize);
    }

    /**
     * 测量ScaleView的高度
     */
    private int getHeightSize(int heightMeasureSpec) {
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int height = 0;
        switch (mode) {
            case MeasureSpec.AT_MOST:
                height = textSize + scaleSpaceText + scaleHeight;
                break;
            case MeasureSpec.EXACTLY: {
                height = MeasureSpec.getSize(heightMeasureSpec);
                break;
            }
            case MeasureSpec.UNSPECIFIED: {
                height = Math.max(textSize + scaleSpaceText + scaleHeight, MeasureSpec.getSize(heightMeasureSpec));
                break;
            }
        }
        return height;
    }


    /**
     * 根据缩放级别更新ScaleView的文字以及比例尺的长度
     */
    double metersPerPixel = 0;

    public void refreshScaleView(MapboxMap mapboxMap) {
        if (mapboxMap == null) {
            return;
        }
        metersPerPixel = mapboxMap.getProjection().getMetersPerPixelAtLatitude(mapboxMap.getCameraPosition().target.getLatitude());//获取在纬度上每像素代表的距离(米)
        int curZoom = (int) Math.round(mapboxMap.getCameraPosition().zoom);
        switch (curZoom) {
            case 2:
                text = "1000km";
                scaleWidth = (int) (1000000 / metersPerPixel);
                break;
            case 3:
                text = "500km";
                scaleWidth = (int) (500000 / metersPerPixel);
                break;
            case 4:
                text = "200km";
                scaleWidth = (int) (200000 / metersPerPixel);
                break;
            case 5:
                text = "100km";
                scaleWidth = (int) (100000 / metersPerPixel);
                break;
            case 6:
                text = "50km";
                scaleWidth = (int) (50000 / metersPerPixel);
                break;
            case 7:
                text = "20km";
                scaleWidth = (int) (20000 / metersPerPixel);
                break;
            case 8:
                text = "10km";
                scaleWidth = (int) (10000 / metersPerPixel);
                break;
            case 9:
                text = "5km";
                scaleWidth = (int) (5000 / metersPerPixel);
                break;
            case 10:
                text = "2km";
                scaleWidth = (int) (2000 / metersPerPixel);
                break;
            case 11:
                text = "1km";
                scaleWidth = (int) (1000 / metersPerPixel);
                break;
            case 12:
                text = "500m";
                scaleWidth = (int) (500 / metersPerPixel);
                break;
            case 13:
                text = "200m";
                scaleWidth = (int) (200 / metersPerPixel);
                break;
            case 14:
                text = "100m";
                scaleWidth = (int) (100 / metersPerPixel);
                break;
            case 15:
                text = "50m";
                scaleWidth = (int) (50 / metersPerPixel);
                break;
            case 16:
                text = "20m";
                scaleWidth = (int) (20 / metersPerPixel);
                break;
            case 17:
                text = "10m";
                scaleWidth = (int) (10 / metersPerPixel);
                break;
            case 18:
                text = "5m";
                scaleWidth = (int) (5 / metersPerPixel);
                break;
            default:
                break;
        }
        this.postInvalidate();
    }
}
