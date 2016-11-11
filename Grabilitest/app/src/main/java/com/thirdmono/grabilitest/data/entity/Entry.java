package com.thirdmono.grabilitest.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class Entry {

    @SerializedName("im:name")
    private ImName imName;

    @SerializedName("im:image")
    private List<ImImage> imImage = new ArrayList<>();

    @SerializedName("summary")
    private Summary summary;

    @SerializedName("im:price")
    private ImPrice imPrice;

    @SerializedName("im:contentType")
    private ImContentType imContentType;

    @SerializedName("rights")
    private Rights rights;

    @SerializedName("title")
    private Title title;

    @SerializedName("link")
    private Link link;

    @SerializedName("id")
    private Id id;

    @SerializedName("im:artist")
    private ImArtist imArtist;

    @SerializedName("category")
    private Category category;

    @SerializedName("im:releaseDate")
    private ImReleaseDate imReleaseDate;

    public Entry() {
    }

    public ImName getImName() {
        return imName;
    }

    public void setImName(ImName imName) {
        this.imName = imName;
    }

    public List<ImImage> getImImage() {
        return imImage;
    }

    public void setImImage(List<ImImage> imImage) {
        this.imImage = imImage;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public ImPrice getImPrice() {
        return imPrice;
    }

    public void setImPrice(ImPrice imPrice) {
        this.imPrice = imPrice;
    }

    public ImContentType getImContentType() {
        return imContentType;
    }

    public void setImContentType(ImContentType imContentType) {
        this.imContentType = imContentType;
    }

    public Rights getRights() {
        return rights;
    }

    public void setRights(Rights rights) {
        this.rights = rights;
    }

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public ImArtist getImArtist() {
        return imArtist;
    }

    public void setImArtist(ImArtist imArtist) {
        this.imArtist = imArtist;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ImReleaseDate getImReleaseDate() {
        return imReleaseDate;
    }

    public void setImReleaseDate(ImReleaseDate imReleaseDate) {
        this.imReleaseDate = imReleaseDate;
    }
}
