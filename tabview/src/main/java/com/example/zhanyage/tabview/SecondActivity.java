package com.example.zhanyage.tabview;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.zhanyage.tabview.adapter.MainPageAdapter;
import com.example.zhanyage.tabview.fragment.SwitchTabView;
import com.example.zhanyage.tabview.fragment.TestFragmentFour;
import com.example.zhanyage.tabview.fragment.TestFragmentOne;
import com.example.zhanyage.tabview.fragment.TestFragmentThree;
import com.example.zhanyage.tabview.fragment.TestFragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanyage on 2020/7/12
 * Describe:
 */
public class SecondActivity extends AppCompatActivity {

    private ViewPager vpFragments;
    private MainPageAdapter mainPageAdapter;
    private SwitchTabView stv_tab_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        init();
    }

    private void init() {
        vpFragments = findViewById(R.id.vp_fragments);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new TestFragmentOne());
        fragmentList.add(new TestFragmentTwo());
        fragmentList.add(new TestFragmentThree());
        fragmentList.add(new TestFragmentFour());
        mainPageAdapter = new MainPageAdapter(this.getSupportFragmentManager(), fragmentList);
        vpFragments.setAdapter(mainPageAdapter);
        vpFragments.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                stv_tab_view.onPageChange(position, positionOffset);
                Log.i("MainActivity","position:" + position + "    positionOffset:" + positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                stv_tab_view.onPageSelect(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        stv_tab_view = findViewById(R.id.stv_tab_view);
        stv_tab_view.setTabClickListener(new SwitchTabView.TabClickListener() {
            @Override
            public void onTabClick(int position) {
                vpFragments.setCurrentItem(position);
            }
        });
    }
}
