package com.zamil.appbelajar.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zamil.appbelajar.R;

@SuppressLint("ValidFragment")
public class ArticleFragment extends Fragment {
    private Context mContext;

    public ArticleFragment(Context context){
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myFragmentView = LayoutInflater.from(mContext).inflate(R.layout.fragment_article, null, false);
        return myFragmentView;
    }
}
