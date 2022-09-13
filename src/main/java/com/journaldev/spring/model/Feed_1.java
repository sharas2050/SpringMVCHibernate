package com.journaldev.spring.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Stores an RSS feed
 */
public class Feed_1 {

    final String url;
    final String title;
    final java.util.Date pubDate;
    final String description;

    final List<FeedMessage> entries = new ArrayList<FeedMessage>();

    public Feed_1(String url, String title, java.util.Date pubDate, String description) {
        this.url = url;
        this.title = title;
        this.pubDate = pubDate;
        this.description = description;
    }

    public List<FeedMessage> getMessages() {
        return entries;
    }

    public String getTitle() {
        return title;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Feed [url=" + url + ", pubDate="
                + pubDate + ", title=" + title + ", feed_name=" + description + "]";
    }

}