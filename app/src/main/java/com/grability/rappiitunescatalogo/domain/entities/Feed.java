
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feed {

    @SerializedName("author")
    private Author author;

    @SerializedName("entry")
    private List<Entry> entry = null;

    @SerializedName("updated")
    private Updated updated;

    @SerializedName("rights")
    private FeedRights rights;

    @SerializedName("title")
    private FeedTitle title;

    @SerializedName("icon")
    private Icon icon;

    @SerializedName("link")
    private List<FeedLink> link = null;

    @SerializedName("id")
    private FeedId id;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }

    public Updated getUpdated() {
        return updated;
    }

    public void setUpdated(Updated updated) {
        this.updated = updated;
    }

    public FeedRights getRights() {
        return rights;
    }

    public void setRights(FeedRights rights) {
        this.rights = rights;
    }

    public FeedTitle getTitle() {
        return title;
    }

    public void setTitle(FeedTitle title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<FeedLink> getLink() {
        return link;
    }

    public void setLink(List<FeedLink> link) {
        this.link = link;
    }

    public FeedId getId() {
        return id;
    }

    public void setId(FeedId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Feed{" +
                ", author=" + author +
                ", entry=" + entry +
                ", updated=" + updated +
                ", rights=" + rights +
                ", title=" + title +
                ", icon=" + icon +
                ", link=" + link +
                ", id=" + id +
                '}';
    }
}
