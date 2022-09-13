package com.journaldev.spring.service;

import com.journaldev.spring.dao.FeedDAO;
import com.journaldev.spring.model.Feed;
import com.journaldev.spring.model.FeedMessage;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FeedServiceTest {
    /**
     * Autowire in the service we want to test
     */
    @InjectMocks
    private FeedServiceImpl service;

    /**
     * Create a mock implementation of the Feed
     */
    @Mock
    private FeedDAO repoMock;

    private Feed feed1,feed2;

    private FeedMessage feedMessage;

    @Before
    public void init() throws ParseException {

        feedMessage = new FeedMessage();
        feedMessage.setId(1);
        feedMessage.setLink("Link");
        feedMessage.setDescription("description");
        feedMessage.setFeed_title("title");
        feedMessage.setPublished(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-11 23:11:24"));

        feed1 = new Feed();
        feed1.setId(1);
        feed1.setUrl("https://www.15min.lt/rss");
        feed1.setTitle("15min.lt - suprasti akimirksniu | RSS");
        feed1.setLast_update(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-11 23:11:24"));
        feed1.getMessages().add(feedMessage);

        feed2 = new Feed();
        feed2.setId(2);
        feed2.setUrl("https://www.lrytas.lt/rss");
        feed2.setTitle("http://www.lrytas.lt");
        feed2.setLast_update(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-11 23:11:24"));



    }

    @Test
    public void testListFeeds() {

        // Data preparation
        Mockito.when(repoMock.listFeeds()).thenReturn(Arrays.asList(feed1, feed2));
//        doReturn(Arrays.asList(feed1, feed2)).when(repoMock).listFeeds();
        // Method call
        List<Feed> feeds = service.listFeeds();

        Assertions.assertEquals(2, feeds.size(), "listFeeds() should return 2 feeds");
    }

    @Test
    public void testGetFeedItemsById() {

        Mockito.when(repoMock.getFeedItemsById(1)).thenReturn(feed1.getMessages());

        // Execute the service call
        List<FeedMessage> feedMessages = service.getFeedItemsById(1);

        // Assert the response
        Assertions.assertFalse(feedMessages.isEmpty(), "Feed Messages was not found");
        Assertions.assertSame(feedMessages.get(0), feedMessage, "Feed Message returned was not the same as the mock");
        }
}