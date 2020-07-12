package com.example.zhanyage.tabview.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.zhanyage.tabview.R;

/**
 * Created by zhanyage on 2020/7/3
 * Describe: viewparger tab 展示 view
 */
public class SwitchTabView extends FrameLayout {

    private int tabNumber = 2;
    private View maskView;
    private LinearLayout tabParent;
    private LayoutInflater layoutInflater;
    private String[] tabNames;
    private int tabItemWidth = 0;
    private TextView[] tabViews = new TextView[100];
    private TabClickListener tabClickListener;

    public SwitchTabView(@NonNull Context context) {
        this(context, null);
    }

    public SwitchTabView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwitchTabView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    public void setTabClickListener(TabClickListener tabClickListener) {
        this.tabClickListener = tabClickListener;
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.switchtabview, defStyleAttr, 0);
            tabNumber = a.getInteger(R.styleable.switchtabview_tabnumber, 2);
            if (a.getString(R.styleable.switchtabview_tabtext) != null) {
                tabNames = a.getString(R.styleable.switchtabview_tabtext).split(",");
            }
            a.recycle();
        }
        layoutInflater = LayoutInflater.from(getContext());
        addSelectView();
        addTabView();
    }

    private void addSelectView() {
        maskView = layoutInflater.inflate(R.layout.view_tab_select, null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        maskView.setLayoutParams(layoutParams);
        addView(maskView);
    }

    private void addTabView() {
        tabParent = (layoutInflater.inflate(R.layout.layout_tag_parent, null)).findViewById(R.id.tab_parent);
        for (int i = 0; i < tabNumber; i++) {
            View view = layoutInflater.inflate(R.layout.view_tab_item, null);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = 1;
            view.setLayoutParams(layoutParams);
            TextView textView = view.findViewById(R.id.tv_tab_item);
            textView.setText(tabNames[i]);
            final int currentPosition = i;
            textView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tabClickListener != null) {
                        tabClickListener.onTabClick(currentPosition);
                    }
                }
            });
            tabParent.addView(view);
            tabViews[i] = textView;
        }
        addView(tabParent);
    }

    private void setTabTextColor(int position) {
        if (position > tabViews.length) {
            return;
        }
        for (int i = 0; i < tabNumber; i++) {
            if (position == i) {
                tabViews[i].setTextColor(getColor(R.color.black));
            } else {
                tabViews[i].setTextColor(getColor(R.color.white));
            }
        }
    }

    @ColorInt
    public final int getColor(@ColorRes int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) maskView.getLayoutParams();
        layoutParams.width = getMeasuredWidth() / tabNumber;
        maskView.setLayoutParams(layoutParams);
        if (tabItemWidth == 0) {
            tabItemWidth = getMeasuredWidth() / tabNumber;
        }
    }

    public void onPageChange(int position, float positionOffset) {
        if (maskView != null && maskView.getLayoutParams() != null) {
            FrameLayout.LayoutParams maskLayoutParams = (FrameLayout.LayoutParams) maskView.getLayoutParams();
            maskLayoutParams.leftMargin = (int) (position * tabItemWidth + positionOffset * tabItemWidth);
            maskView.setLayoutParams(maskLayoutParams);
        }
    }

    public void onPageSelect(int position) {
        setTabTextColor(position);
    }


    public interface TabClickListener {
        void onTabClick(int position);
    }
}
