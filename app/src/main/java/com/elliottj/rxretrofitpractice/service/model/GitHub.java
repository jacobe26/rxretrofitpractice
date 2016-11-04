package com.elliottj.rxretrofitpractice.service.model;

/**
 * Created by elliottj on 11/3/16.
 */

public class GitHub {

    private String login;
    private String blog;
    private int public_repos;

    public String getLogin() {
        return login;
    }

    public String getBlog() {
        return blog;
    }

    public int getPublicRepos() {
        return public_repos;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("login = " + login + "\n");
        builder.append("blog = " + blog + "\n");
        builder.append("public_repos = " + public_repos);
        return builder.toString();
    }
}
