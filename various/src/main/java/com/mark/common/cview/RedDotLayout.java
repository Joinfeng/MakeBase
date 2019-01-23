package com.mark.common.cview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import com.mark.common.R;

/**
 * @author Marks
 * date: On 2018/7/12 ${time}
 * e-mail:mottoboy@126.com
 * describe:
 * <li>来源于：https://blog.csdn.net/lvmingci/article/details/78781622<li>
 */
public class RedDotLayout extends RelativeLayout {

    /**
     * 红点里的文本
     */
    private String text = "0";
    /**
     * 背景颜色：默认为红色
     */
    private int background = Color.RED;
    /**
     * 圆点的半径：默认为30dp
     */
    private int radius = 30;
    /**
     * 内容字体的颜色：默认白色
     */
    private int textColor = Color.WHITE;
    /**
     * 圆点的位置：默认右上角(1:左上角，2：右上角，3：左下角，4：右下角)
     */
    private int location = 2;
    /**
     * 内容字体大小：默认13sp
     */
    private int textSize = 20;
    /**
     * 画笔
     */
    private Paint mPaint = new Paint();
    /**
     * 绘制时控制文本绘制的范围
     */
    private Rect mBound = new Rect();

    public RedDotLayout(Context context) {
        this(context, null);
    }

    public RedDotLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RedDotLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取xml中的自定义样式
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RedDotLayout);
        int n = array.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = array.getIndex(i);
            //背景
            //半径
            //内容
            //内容字体颜色
            //获取圆点的位置
            //获取内容字体大小
            if (attr == R.styleable.RedDotLayout_red_background) {
                background = array.getColor(attr, Color.RED);

            } else if (attr == R.styleable.RedDotLayout_red_radius) {
                radius = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()));

            } else if (attr == R.styleable.RedDotLayout_red_text) {
                text = array.getString(attr);

            } else if (attr == R.styleable.RedDotLayout_red_textColor) {
                textColor = array.getColor(attr, Color.WHITE);

            } else if (attr == R.styleable.RedDotLayout_red_location) {
                location = array.getInteger(attr, 2);

            } else if (attr == R.styleable.RedDotLayout_red_textSize) {
                textSize = array.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_SP, 20, getResources().getDisplayMetrics()));

            } else {
            }
        }
        //释放资源
        array.recycle();
    }

    /**
     * 清除内容：清除内容后会去掉圆点
     */
    public void clear() {
        setText("");
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (TextUtils.isEmpty(text)) {
            return;
        }
        //设置画笔抗钜齿
        mPaint.setAntiAlias(true);
        //画背景色
        mPaint.setColor(background);
        int cx = 0;
        int cy = 0;
        switch (location) {
            case 1:
                //左上角
                cx = radius;
                cy = radius;
                break;
            case 2:
                //右上角
                cx = getWidth() - radius;
                cy = radius;
                break;
            case 3:
                //左下角
                cx = radius;
                cy = getHeight() - radius;
                break;
            case 4:
                //右下角
                cx = getWidth() - radius;
                cy = getHeight() - radius;
                break;
            default:
        }
        canvas.drawCircle(cx, cy, radius, mPaint);
        mPaint.reset();
        //画内容文字
        //得到绘制文本的范围
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.getTextBounds(text, 0, text.length(), mBound);
        //防止内容太多造成的显示不全
        if (mBound.width() >= radius * 2 || mBound.height() >= radius * 2) {
            text = "...";
        }
        canvas.drawText(text, cx - (mBound.width() / 2), cy + (mBound.height() / 2), mPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    public void setText(String text) {
        this.text = text;
        postInvalidate();
    }
}
