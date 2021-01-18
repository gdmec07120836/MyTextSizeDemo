package com.xgh.mytextsizedemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Time:2021/1/15
 * Author:xgh
 * Description:动态文字尺寸的TextView（根据TextView所占的宽度来动态设置TextSize）
 */
public class CustomDynamicSizeTextView extends AppCompatTextView {
    private static final String TAG = "CustomDynamicSizeTextView";
    private int measuredHeight;
    private int measuredWidth;//控件的最大宽度
    private int textActualWidth;//文字的实际占用宽度
    private boolean isCalculationComplete = false;//文字宽高是否计算完成
    private float oldTextSize = 0;//记录初始TextSize

    public CustomDynamicSizeTextView(Context context) {
        super(context);
        //记录一下TextSize
        setOldTextSize(getTextSize());
    }

    public CustomDynamicSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //记录一下TextSize
        setOldTextSize(getTextSize());
    }

    public CustomDynamicSizeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //记录一下TextSize
        setOldTextSize(getTextSize());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.measuredHeight = getTextHeight();
        this.measuredWidth = getTextWidth(widthMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    /**
     * 获取文字实际占用宽度
     *
     * @return
     */
    private int getTextWidth(int widthMeasureSpec) {
        if (getText() == null || TextUtils.isEmpty(getText().toString())) {
            return 0;
        }
        int w1 = MeasureSpec.getSize(widthMeasureSpec);

        Paint paint = new Paint();
        paint.setTextSize(getOldTextSize());
        textActualWidth = (int) paint.measureText(getText().toString());

        int width = 0;
        if (textActualWidth > w1) {
            width = w1;
        } else {
            width = textActualWidth;
        }
        return width;
    }

    /**
     * 获取文字实际占用高度
     *
     * @return
     */
    private int getTextHeight() {
        if (getText() == null || TextUtils.isEmpty(getText().toString())) {
            return 0;
        }
        Rect rect = new Rect();
        Paint paint = new Paint();
        if (isCalculationComplete){
            paint.setTextSize(getTextSize());
        }
        else {
            paint.setTextSize(getOldTextSize());
        }
        paint.getTextBounds(getText().toString(), 0, getText().toString().length(), rect);
        Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
        int height = fontMetricsInt.bottom - fontMetricsInt.top;
        return height;
    }
    private void setTextHeight() {
        if (getText() == null || TextUtils.isEmpty(getText().toString())) {
            return;
        }
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().toString().length(), rect);
        Paint.FontMetricsInt fontMetricsInt = getPaint().getFontMetricsInt();
        int height = fontMetricsInt.bottom - fontMetricsInt.top;
        setHeight(height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isCalculationComplete) {
            reMeasure();
        }
        super.onDraw(canvas);
    }
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        isCalculationComplete = false;
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    /**
     * 根据控件的宽度 重新设置文字的大小 让其能一行完全显示
     */
    @SuppressLint("LongLogTag")
    private void reMeasure() {
        float size = getOldTextSize();

        if (textActualWidth+3>measuredWidth){
            double magnification = Arith.div(textActualWidth, measuredWidth);
            size = (float) Math.ceil(Arith.div(getOldTextSize()*1.0, magnification));
            //先大致设置一个尺寸
            Paint roughlyPaint = new Paint();
            roughlyPaint.setTypeface(getPaint().getTypeface());
            roughlyPaint.setTextScaleX(getPaint().getTextScaleX());
            roughlyPaint.setTextSize(size);
            int width = (int) roughlyPaint.measureText(getText().toString());
            //如果还是太大，再慢慢细调
            while (width+3 > measuredWidth) {
                size--;
                //设置文字大小
                Paint tempPaint = new Paint();
                tempPaint.setTypeface(getPaint().getTypeface());
                tempPaint.setTextScaleX(getPaint().getTextScaleX());
                tempPaint.setTextSize(size);
                width = (int) tempPaint.measureText(getText().toString());
            }
        }
        //设置文字大小
        setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
        isCalculationComplete = true;
        //重新设置控件高度
        setTextHeight();
    }

    public float getOldTextSize() {
        return oldTextSize;
    }

    public void setOldTextSize(float oldTextSize) {
        this.oldTextSize = oldTextSize;
    }
}
