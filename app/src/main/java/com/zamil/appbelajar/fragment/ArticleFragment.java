package com.zamil.appbelajar.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zamil.appbelajar.R;
import com.zamil.appbelajar.activity.AddArticleActivity;
import com.zamil.appbelajar.helper.database.DatabaseClient;
import com.zamil.appbelajar.model.ArticleEntity;

import java.util.List;

@SuppressLint("ValidFragment")
public class ArticleFragment extends Fragment {
    private Context mContext;
    Button buttonAddArticle;

    public ArticleFragment(Context context){
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myFragmentView = LayoutInflater.from(mContext).inflate(R.layout.fragment_article, null, false);

        loadData();

        buttonAddArticle = myFragmentView.findViewById(R.id.add_article);
        buttonAddArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, AddArticleActivity.class));
            }
        });

        return myFragmentView;
    }

    public void loadData(){

        class GetData extends AsyncTask<Void, Void, List<ArticleEntity>> {

            @Override
            protected List<ArticleEntity> doInBackground(Void... voids) {

                List<ArticleEntity> data = DatabaseClient.getInstance(mContext)
                        .getAppDatabase()
                        .articleDao()
                        .getAll();
                Log.d("Data :", String.valueOf(data.size()));
                return data;
            }
        }

        GetData getData = new GetData();
        getData.execute();
    }
}
