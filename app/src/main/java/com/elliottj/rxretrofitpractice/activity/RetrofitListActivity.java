package com.elliottj.rxretrofitpractice.activity;

import android.database.Observable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.elliottj.rxretrofitpractice.R;
import com.elliottj.rxretrofitpractice.adapter.CardAdapter;
import com.elliottj.rxretrofitpractice.data.Accounts;
import com.elliottj.rxretrofitpractice.service.GithubService;
import com.elliottj.rxretrofitpractice.service.ServiceFactory;
import com.elliottj.rxretrofitpractice.service.model.GitHub;

import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RetrofitListActivity extends AppCompatActivity {

    public static final String CLS_TAG = RetrofitListActivity.class.getSimpleName();

    private CardAdapter mCardAdapter;
    private SwipeRefreshLayout mSwipeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_list);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.github_items_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter = new CardAdapter();
        mRecyclerView.setAdapter(mCardAdapter);

        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mCardAdapter.clear();

                GithubService service = ServiceFactory.createRetrofitService(GithubService.class, GithubService.SERVICE_ENDPOINT);
                for (String login : Accounts.githubAccountsList) {
                    service.getUser(login)
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<GitHub>() {
                                @Override
                                public void onCompleted() {
                                    Log.d(CLS_TAG, "onCompleted");
                                    mSwipeLayout.setRefreshing(false);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.e(CLS_TAG, "onError: " + e.getMessage());
                                }

                                @Override
                                public void onNext(GitHub gitHub) {
                                    Log.d(CLS_TAG, "onNext: \n" + gitHub.toString());
                                    mCardAdapter.addData(gitHub);
                                }
                            });
                }
            }
        });
    }
}
