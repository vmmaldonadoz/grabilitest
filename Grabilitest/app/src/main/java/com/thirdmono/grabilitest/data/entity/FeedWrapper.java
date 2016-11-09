package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;

public class FeedWrapper {

    @SerializedName("feed")
    private Feed feed;

    public FeedWrapper() {

    }

    public Feed getFeed() {
        return feed;
    }

    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
