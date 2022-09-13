package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Feed;
import com.journaldev.spring.model.FeedMessage;

public interface FeedDAO {

	public void addFeed(Feed p);
	public void updateFeed(Feed p);
	public List<Feed> listFeeds();
	public Feed getFeedById(int id);
	public void removeFeed(int id);

	public List<FeedMessage> getFeedItemsById(int id);
}
