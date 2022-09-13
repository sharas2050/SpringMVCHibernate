package com.journaldev.spring.model;

import javax.persistence.*;
import java.util.Date;

/*
 * Represents one RSS message
 */
@Entity
@Table(name="ITEMS")
public class FeedMessage {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private String feed_title;

    private String description;

    private String link;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date published;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFeed_title() {
        return feed_title;
    }

    public void setFeed_title(String feed_title) {
        this.feed_title = feed_title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPublished() {
        return published;
    }

    public void setPublished(Date published) {
        this.published = published;
    }

    @Override
    public String toString() {
        return "FeedMessage [title=" + feed_title + ", link=" + link + ", description=" + description + ", published=" + published
                + "]";
    }

}