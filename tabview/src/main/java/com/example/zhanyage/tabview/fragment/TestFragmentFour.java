package com.example.zhanyage.tabview.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.zhanyage.switchtabview.R;
import com.example.zhanyage.tabview.R;

/**
 * Created by zhanyage on 2020/7/10
 * Describe:
 */
public class TestFragmentFour extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_four, null);
    }
}
