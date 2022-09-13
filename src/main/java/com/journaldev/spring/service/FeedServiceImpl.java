package com.journaldev.spring.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.journaldev.spring.dao.FeedDAO;
import com.journaldev.spring.model.Feed;
import com.journaldev.spring.model.FeedMessage;

@Service
public class FeedServiceImpl implements FeedService {
	
	private FeedDAO feedDAO;

	public void setFeedDAO(FeedDAO feedDAO) {
		this.feedDAO = feedDAO;
	}

	@Override
	@Transactional
	public void addFeed(Feed f) {
		this.feedDAO.addFeed(f);
	}

	@Override
	@Transactional
	public void updateFeed(Feed f) {
		this.feedDAO.updateFeed(f);
	}

	@Override
	@Transactional
	public List<Feed> listFeeds() {
		return this.feedDAO.listFeeds();
	}

	@Override
	@Transactional
	public Feed getFeedById(int id) {
		return this.feedDAO.getFeedById(id);
	}

	@Override
	@Transactional
	public void removeFeed(int id) {
		this.feedDAO.removeFeed(id);
	}

	@Override
	@Transactional
	public List<FeedMessage> getFeedItemsById(int id) {
		return this.feedDAO.getFeedItemsById(id);
	}
}
