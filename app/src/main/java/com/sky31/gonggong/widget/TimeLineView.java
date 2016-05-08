package com.sky31.gonggong.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.sky31.gonggong.R;
import com.sky31.gonggong.config.CommonFunction;

/**
 * Created by wukunguang on 16-5-5.
 */
public class TimeLineView extends View {


    private Context context;

    private boolean isPast = true;

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint = new Paint();

        //mCanva = canvas;
        //获取颜色
        int width = getWidth();
        int height = getHeight();
        float piex = CommonFunction.convertDpToPixel(26.0f,context);
        float lineWidth = CommonFunction.convertDpToPixel(2.0f,context);
        paint.setColor(context.getResources().getColor(R.color.color_0176da));
        paint.setStrokeWidth(lineWidth);
        canvas.drawLine(width/2,0,width/2,piex-15,paint);
        canvas.drawLine(width/2,piex+15,width/2,height,paint);
        //paint.reset();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
//        Log.d("customerView_onDraw",isPast+"");
//        if (!isPast){
//            paint.setColor(context.getResources().getColor(R.color.color_0176da));
//        }
//        else {
//            paint.setColor(context.getResources().getColor(R.color.grey_d));
//        }
//        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(5);
        canvas.drawCircle(width/2,piex,10,paint);
    }






    public TimeLineView(Context context, boolean isPast) {
        super(context);


        Log.d("customerView",isPast+"");
        this.isPast = isPast;
        this.context = context;
    }



    public TimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TimeLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

}
