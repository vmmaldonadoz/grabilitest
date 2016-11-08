package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Feed {
    @SerializedName("entry")
    private List<Entry> entries;
}
