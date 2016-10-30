package cc.xaabb.gonggong.widget;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;

import com.gc.materialdesign.views.ProgressBarCircularIndeterminate;
import com.sky31.gonggong.R;

/**
 * Created by wukunguang on 16-3-30.
 */
public class RefreshListView extends ListView implements AbsListView.OnScrollListener{


    View headerView;    //顶部View
    private int headerHeight;   //顶部布局文件高度

    private int firstVisibleItem;   //当前可见第一个的元素位置。

    private boolean isInFirstDown;  //表示在最顶端按下。
    private int startY; //  按下时候开始的Y数值
    private Runnable runnable;


    private int currentState;//定义当前状态位
    private final int NONE = 0;//无状态，即什么都没有
    private final int PULL = 1;//当前下拉状态
    private final int RELESE = 2;//提示可释放状态
    private final int REFRESHING = 3;//正在刷新状态

    private View footerView;
    private Resources resources;

    private int scrollState;//当前滚动状态
    /**
     * 初始化界面，添加顶部布局View
     * @param ctx
     */
    private void initView(Context ctx){

        LayoutInflater inflater = LayoutInflater.from(ctx);
        headerView = inflater.inflate(R.layout.fresh_listview_header, null);
        footerView = inflater.inflate(R.layout.swzl_nomore_data,null);

        addFooterView(footerView);
        //获得资源。
        resources = ctx.getResources();

        measureView(headerView);
        headerHeight = headerView.getMeasuredHeight();
        Log.i("refreshListView",headerHeight+">>>>>>");
        setTopHeaderViewHeight(-headerHeight);
        this.addHeaderView(headerView);
        this.setOnScrollListener(this);

    }


    /**
     * 通知父类布局占用其内容大小
     * @param view  //顶部View
     */
    private void measureView(View view){


        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null){
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int width = ViewGroup.getChildMeasureSpec(0,0,p.width);
        int height;
        int tempHeight = p.height;
        if(tempHeight > 0){
            height = MeasureSpec.makeMeasureSpec(tempHeight,MeasureSpec.EXACTLY);
        }
        else {
            height = MeasureSpec.makeMeasureSpec( 0 ,MeasureSpec.UNSPECIFIED);
        }
        view.measure(width, height);

    }

    public RefreshListView(Context context) {
        super(context);
        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public RefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    /**
     * 设置它的边距。用于下拉显示和默认隐藏
     * @param topPadding    //顶部边距
     */
    private void setTopHeaderViewHeight(int topPadding){

        headerView.setPadding(headerView.getPaddingLeft(), topPadding, headerView.getPaddingRight(), headerView.getPaddingBottom());
        headerView.invalidate();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        this.scrollState = scrollState;
        Log.d("freshListView", "scrollState:" + scrollState + "");
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.firstVisibleItem = firstVisibleItem;

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                setRefreshViewByState();
                if (firstVisibleItem == 0){
                    //代表当前最顶端
                    isInFirstDown = true;
                    startY = (int) ev.getY();

                }

                break;
            case MotionEvent.ACTION_MOVE:
                setRefreshViewByState();
                onMove(ev);
                break;

            case MotionEvent.ACTION_UP:

                if (RELESE == currentState){
                    //设置刷新。
                    currentState = REFRESHING;
                    setRefreshViewByState();
                }else if (currentState == PULL){

                    currentState = NONE;
                    isInFirstDown = false;
                    setRefreshViewByState();
                }

                break;

        }

        return super.onTouchEvent(ev);
    }


    /**
     * 移动时候执行的操作。
     * @param ev    事件
     */
    private void onMove(MotionEvent ev){

         if (!isInFirstDown){
             return;
         }


        int tempY = (int) ev.getY();    //获取当前滑动时候的Y的坐标。
        int moveSpace = tempY - startY; //获取坐标差。
        int topPadding = moveSpace - headerHeight;  //获取需要显示出来的高度
        Log.d("freshListView","state:"+currentState+"----");
        //Log.d("frechListView","headerHeight"+headerHeight+"---");
        switch (currentState){
            case NONE:

                if (moveSpace > 0){
                    currentState = PULL;
                    setRefreshViewByState();
                }

                break;
            case PULL:
                //设置显示高度
                setTopHeaderViewHeight(topPadding);
                //下拉高度差大于30且当前正在下拉时候
                if (moveSpace > headerHeight+30 && scrollState ==SCROLL_STATE_TOUCH_SCROLL){

                    Log.i("refresh_msg","hahahha--");
                    currentState = RELESE;
                    setRefreshViewByState();
                }

                break;
            case RELESE:
                //设置显示高度
                setTopHeaderViewHeight(topPadding);
                if (moveSpace < headerHeight +30  ){

                    currentState = PULL;
                    setRefreshViewByState();

                }else if (moveSpace <= 0){
                    currentState = NONE;
                    isInFirstDown = false;
                    setRefreshViewByState();
                }

                break;
            case REFRESHING:

                break;

        }

    }

    public void initRunable(Runnable runnable){

        this.runnable = runnable;
    }

    /**
     * 根据当前状态改变headerView显示内容。
     */
    private void setRefreshViewByState(){

        TextView textView = (TextView) headerView.findViewById(R.id.refresh_tip);
        ProgressBarCircularIndeterminate progressBar = (ProgressBarCircularIndeterminate)
                headerView.findViewById(R.id.refresh_listView_ProgressBar);


        switch (currentState){

            case NONE:
                setTopHeaderViewHeight(-headerHeight);
                break;
            case PULL:
                progressBar.setVisibility(GONE);
                textView.setText(resources.getString(R.string.fresh_listView_pullTip));
                break;
            case RELESE:
                progressBar.setVisibility(GONE);
                textView.setText(resources.getString(R.string.fresh_listView_releaseTip));
                break;
            case REFRESHING:

                runnable.run();



                setTopHeaderViewHeight(headerHeight);
                progressBar.setVisibility(VISIBLE);

                textView.setText(resources.getString(R.string.fresh_listView_freshNow));
                Log.d("refresh_msg", "Refreshing!");


                break;

        }

    }

    public void dismissHeaderView(){

        currentState = NONE;
        isInFirstDown = false;
        setRefreshViewByState();

        //TextView tx = (TextView) headerView.findViewById(R.id.refresh_tip);

    }

}
