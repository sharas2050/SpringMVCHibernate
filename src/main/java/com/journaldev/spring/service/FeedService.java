package com.journaldev.spring.service;

import java.util.List;

import com.journaldev.spring.model.Feed;
import com.journaldev.spring.model.FeedMessage;
import org.springframework.stereotype.Service;

@Service
public interface FeedService {

	public void addFeed(Feed p);
	public void updateFeed(Feed p);
	public List<Feed> listFeeds();
	public Feed getFeedById(int id);
	public void removeFeed(int id);

	public List<FeedMessage> getFeedItemsById(int id);
}
