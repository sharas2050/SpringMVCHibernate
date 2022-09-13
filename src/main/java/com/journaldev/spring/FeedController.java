package com.journaldev.spring;

import com.journaldev.spring.model.Feed;
import com.journaldev.spring.model.FeedMessage;
import com.journaldev.spring.model.Feed_1;
import com.journaldev.spring.service.FeedService;
import com.journaldev.spring.service.RSSFeedParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;


@Controller
public class FeedController {
	
	private FeedService feedService;
	private RSSFeedParser rssFeedParser;
	
	@Autowired(required=true)
	@Qualifier(value="feedService")
	public void setFeedService(FeedService fs){
		this.feedService = fs;
	}
	
	@RequestMapping(value = "/feeds", method = RequestMethod.GET)
	public String listFeeds(Model model) {
		model.addAttribute("feed", new Feed());
		model.addAttribute("listFeeds", this.feedService.listFeeds());
		return "feed";
	}
	
	//For add and update feed both
	@RequestMapping(value= "/feeds/add", method = RequestMethod.POST)
	public String addFeed(@ModelAttribute("feed") Feed f){

		if(f.getId() == 0){
			//new feed, add it
			RSSFeedParser parser = new RSSFeedParser(
					f.getUrl());

			Feed_1 feed = parser.readFeed();

			f.setUrl(feed.getUrl());
			f.setTitle(feed.getTitle());
			f.setLast_update(feed.getPubDate());
			f.setFeed_name(feed.getDescription());

			List<FeedMessage> first5Items = feed.getMessages()
					.stream().limit(5).collect(Collectors.toList());

			for (FeedMessage message : first5Items) {


				f.getMessages().add(message);

				System.out.println(message);

			}

			this.feedService.addFeed(f);
		}else{
			//existing feed, call update
			this.feedService.updateFeed(f);
		}
		
		return "redirect:/feeds";
		
	}

	@RequestMapping("feeds/details/{id}")
	public String getFeedDetailsById(@PathVariable("id") int id, Model model){

		model.addAttribute("messages", new FeedMessage());
		model.addAttribute("listMessagesById", this.feedService.getFeedItemsById(id));

		return "messages";
	}

}
