package com.test.holy.onlyholy.myView.listView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.holy.onlyholy.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by houlin.jiang on 2017/3/9.
 */

public class PullToRefreshListView extends ListView implements OnScrollListener{
    private static final String TAG = "PullToRefreshListView";

    private final int DOWN_PULL_REFRESH = 0; // 下拉刷新状态
    private final int RELEASE_REFRESH = 1; // 松开刷新
    private final int REFRESHING = 2; // 正在刷新中

    private int currentState = DOWN_PULL_REFRESH; // 头布局的状态: 默认为下拉刷新状态
    private View headerView, footerView;
    private TextView tvHeaderTips,tvUpdateTime,tvFooterTips;
    private ProgressBar headerProgressBar,footerProgressBar;
    private String updateTime = "";
    private int headerViewHeight,footerVewHeight,touchDownY;
    private Animation upAnimation,downAnimation;
    private int firstVisibleItemPosition; // 屏幕显示在第一个的item的索引
    private boolean isScrollToBottom,isLoadingMore;
    private OnRefreshListener mOnRefreshListener;


    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initHeaderView();
        initFooterView();
        this.setOnScrollListener(this);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PullToRefreshListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    //==============================================================================================
    //================================    初始化            ========================================
    //==============================================================================================

    public interface OnRefreshListener{
        void onDownPullRefresh();
        void onLoadingMore();
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        mOnRefreshListener = listener;
    }

    private void initHeaderView() {
        headerView = View.inflate(getContext(), R.layout.layout_listview_header,null);
        headerProgressBar = (ProgressBar) headerView.findViewById(R.id.list_view_header_progressbar);
        tvHeaderTips = (TextView) headerView.findViewById(R.id.list_view_header_tips);
        tvUpdateTime = (TextView) headerView.findViewById(R.id.list_view_header_update_time);
        tvUpdateTime.setText("上次刷新时间 ：" + updateTime);

        headerView.measure(0,0);
        headerViewHeight = headerView.getMeasuredHeight();
        headerView.setPadding(0,-headerViewHeight,0,0);
        this.addHeaderView(headerView);
        initAnimation();
    }

    private void initAnimation() {
        upAnimation = new RotateAnimation(0f,-180f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        upAnimation.setDuration(1000);
        upAnimation.setFillAfter(true);

        downAnimation = new RotateAnimation(-180f,-360f,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        downAnimation.setDuration(1000);
        downAnimation.setFillAfter(true);

    }

    private void initFooterView() {
        footerView = View.inflate(getContext(), R.layout.layout_listview_footer,null);
        footerProgressBar = (ProgressBar) footerView.findViewById(R.id.list_view_footer_progressbar);
        tvFooterTips = (TextView) headerView.findViewById(R.id.list_view_footer_tips);

        footerView.measure(0,0);
        footerVewHeight = footerView.getMeasuredHeight();
        footerView.setPadding(0,-footerVewHeight,0,0);
        this.addFooterView(footerView);
    }


    //==============================================================================================
    //================================    点击事件            ======================================
    //==============================================================================================


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :
                touchDownY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE :
                int moveY = (int) ev.getY();
                int diff = moveY - touchDownY;
                int paddingTop = -headerViewHeight + diff;
                if (firstVisibleItemPosition == 0 && diff > 0) {
                    // 下拉中
                    if (paddingTop > 0 && currentState == DOWN_PULL_REFRESH) {
                        currentState = RELEASE_REFRESH;
                        refreshHeaderView();
                    } else if (paddingTop <= 0 && currentState == RELEASE_REFRESH) {
                        currentState = DOWN_PULL_REFRESH;
                        refreshHeaderView();
                    }
                    headerView.setPadding(0, paddingTop, 0, 0);
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP :
                if(currentState == RELEASE_REFRESH) {
                    headerView.setPadding(0,0,0,0);
                    currentState = REFRESHING;
                    refreshHeaderView();
                    if (mOnRefreshListener != null) {
                        mOnRefreshListener.onDownPullRefresh();
                    }
                }
                break;


        }
        return super.onTouchEvent(ev);
    }


    private void refreshHeaderView() {
        switch (currentState) {
            case DOWN_PULL_REFRESH : // 下拉刷新状态
                tvHeaderTips.setText("下拉刷新");
                break;
            case RELEASE_REFRESH : // 松开刷新状态
                tvHeaderTips.setText("松开刷新");
                break;
            case REFRESHING : // 正在刷新中状态
                headerProgressBar.setVisibility(View.VISIBLE);
                tvHeaderTips.setText("正在刷新中...");
                break;
            default :
                break;
        }
    }

    /**
     * 隐藏头布局
     */
    public void hideHeaderView() {
        headerView.setPadding(0, -headerViewHeight, 0, 0);
        headerProgressBar.setVisibility(View.GONE);
        tvHeaderTips.setText("下拉刷新");
        saveUpdateTime();
        currentState = DOWN_PULL_REFRESH;
    }

    private void saveUpdateTime() {
        updateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
    }

    /**
     * 隐藏脚布局
     */
    public void hideFooterView() {
        footerView.setPadding(0, -footerVewHeight, 0, 0);
        isLoadingMore = false;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

        if (scrollState == SCROLL_STATE_IDLE
                || scrollState == SCROLL_STATE_FLING) {
            // 判断当前是否已经到了底部
            if (isScrollToBottom && !isLoadingMore) {
                isLoadingMore = true;
                // 当前到底部
                footerView.setPadding(0, 0, 0, 0);
                this.setSelection(this.getCount());

                if (mOnRefreshListener != null) {
                    mOnRefreshListener.onLoadingMore();
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        firstVisibleItemPosition = firstVisibleItem;
        if (getLastVisiblePosition() == (totalItemCount - 1)) {
            isScrollToBottom = true;
        } else {
            isScrollToBottom = false;
        }
    }
}
