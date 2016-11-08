package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;


public class ImArtist {

    @SerializedName("label")
    private String label;

    @SerializedName("attributes")
    private ArtistAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArtistAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ArtistAttributes attributes) {
        this.attributes = attributes;
    }
}
