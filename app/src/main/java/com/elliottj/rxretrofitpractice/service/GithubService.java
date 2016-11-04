package com.elliottj.rxretrofitpractice.service;

import com.elliottj.rxretrofitpractice.service.model.GitHub;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by elliottj on 11/3/16.
 */

public interface GithubService {
    String SERVICE_ENDPOINT = "https://api.github.com";

    @GET("/users/{login}")
    Observable<GitHub> getUser(@Path("login") String userId);
}
