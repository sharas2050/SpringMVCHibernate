package com.journaldev.spring.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity bean with JPA annotations
 * Hibernate provides JPA implementation
 */
@Entity
@Table(name="FEEDS")
@SecondaryTable(name="ITEMS")
public class Feed {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String url;
	
	private String title;

	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date last_update;

	private String feed_name;

	@OneToMany(
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	@JoinColumn(name = "feed_id", referencedColumnName = "id")
	private List<FeedMessage> messages = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public java.util.Date getLast_update() {
		return last_update;
	}

	public void setLast_update(java.util.Date last_update) {
		this.last_update = last_update;
	}

	public String getFeed_name() {
		return feed_name;
	}

	public void setFeed_name(String feed_name) {
		this.feed_name = feed_name;
	}

	public List<FeedMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<FeedMessage> messages) {
		this.messages = messages;
	}

	@Override
	public String toString(){
		return "id="+id+", url="+url+", title="+title +", last_update="+last_update +", feed_name="+feed_name;
	}

}
