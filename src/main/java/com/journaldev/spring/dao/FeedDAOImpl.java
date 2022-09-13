package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Feed;
import com.journaldev.spring.model.FeedMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class FeedDAOImpl implements FeedDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(FeedDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addFeed(Feed f) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(f);
		logger.info("Feed saved successfully, Feed Details="+f);
	}

	@Override
	public void updateFeed(Feed f) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(f);
		logger.info("Feed updated successfully, Feed Details="+f);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feed> listFeeds() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Feed> feedsList = session.createQuery("from Feed").list();
		for(Feed f : feedsList){
			logger.info("Feeds List::"+f);
		}
		return feedsList;
	}

	@Override
	public Feed getFeedById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Feed f = (Feed) session.load(Feed.class, new Integer(id));
		logger.info("Feed loaded successfully, Feed details="+f);
		return f;
	}

	@Override
	public void removeFeed(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Feed f = (Feed) session.load(Feed.class, new Integer(id));
		if(null != f){
			session.delete(f);
		}
		logger.info("Feed deleted successfully, Feed details="+f);
	}

	@Override
	public List<FeedMessage> getFeedItemsById(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		List<FeedMessage> messageList = session.createQuery("from FeedMessage where feed_id = :feed_id")
				.setParameter("feed_id", id)
				.list();
		for(FeedMessage m : messageList){
			logger.info("Feed Items List::"+m);
		}

		return messageList;
	}

}
