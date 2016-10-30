package cc.xaabb.gonggong.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import cc.xaabb.gonggong.R;
import cc.xaabb.gonggong.config.CommonFunction;

/**
 * Created by wukunguang on 16-5-5.
 */
public class TimeLineView extends View {


    private Context context;
    private Context appContext;

    private boolean isPast = true;

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        Paint paint = new Paint();

        //mCanva = canvas;
        //获取颜色
        int width = getWidth();
        int height = getHeight();
        float piex = CommonFunction.convertDpToPixel(26.0f, appContext);
        float lineWidth = CommonFunction.convertDpToPixel(2.0f, appContext);
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
        appContext = context.getApplicationContext();
    }



    public TimeLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        appContext = context.getApplicationContext();
    }

    public TimeLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        appContext = context.getApplicationContext();
    }

}
