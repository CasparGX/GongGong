package cc.xaabb.gonggong.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroupOverlay;
import android.widget.ExpandableListView;

/**
 * Created by wukunguang on 16-5-17.
 */
public class CustomerExpandableListView extends ExpandableListView {
    public CustomerExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomerExpandableListView(Context context) {
        super(context);
    }

    public CustomerExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {



        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    public ViewGroupOverlay getOverlay() {
        return super.getOverlay();
    }
}
