package com.journaldev.spring.tests;

import com.journaldev.spring.model.FeedMessage;
import com.journaldev.spring.model.Feed_1;
import com.journaldev.spring.service.RSSFeedParser;

import java.util.List;
import java.util.stream.Collectors;

public class ReadTest {
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://www.tv3.lt/rss/sarasas/");
        Feed_1 feed = parser.readFeed();
        System.out.println(feed);

        List<FeedMessage> first5Items = feed.getMessages()
                .stream().limit(5).collect(Collectors.toList());

        for (FeedMessage message : first5Items) {
            System.out.println(message);
            System.out.println(feed.getMessages().size());

        }

    }
}