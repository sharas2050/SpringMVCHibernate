package com.journaldev.spring;

import com.journaldev.spring.model.Feed;
import com.journaldev.spring.model.FeedMessage;
import com.journaldev.spring.service.FeedService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
public class FeedControllerTest {

    @InjectMocks
    private FeedController controller;

    @Mock
    private FeedService feedService;

    private MockMvc mockMvc;


    private Feed feed1,feed2;

    private FeedMessage feedMessage;

    @Before
    public void init() throws ParseException {

        MockitoAnnotations.initMocks(this);

        mockMvc = standaloneSetup(controller).build();

        feedMessage = new FeedMessage();
        feedMessage.setId(2);
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
    public void testControllerFindAll() throws Exception {

        Mockito.when(feedService.listFeeds()).thenReturn(Arrays.asList(feed1, feed2));


        mockMvc.perform(get("/feeds"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("feed"))
                .andExpect(forwardedUrl("feed"))
                .andExpect(model().attributeExists("listFeeds", "feed"))
                .andExpect(model().attribute("listFeeds", hasSize(2)))
                .andExpect(model().attribute("listFeeds", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("url", is("https://www.15min.lt/rss")),
                                hasProperty("title", is("15min.lt - suprasti akimirksniu | RSS")),
                                hasProperty("last_update", is(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-11 23:11:24")))
                        )
                )))
                .andExpect(model().attribute("listFeeds", hasItem(
                        allOf(
                                hasProperty("id", is(2)),
                                hasProperty("url", is("https://www.lrytas.lt/rss")),
                                hasProperty("title", is("http://www.lrytas.lt")),
                                hasProperty("last_update", is(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-09-11 23:11:24")))
                        )
                )));

        verify(feedService, times(1)).listFeeds();
        verifyNoMoreInteractions(feedService);

    }

    @Test
    public void testControllerGetFeedDetailsById() throws Exception {

        Mockito.when(feedService.getFeedItemsById(1)).thenReturn(feed1.getMessages());

        mockMvc.perform(get("/feeds/details/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("messages"))
                .andExpect(forwardedUrl("messages"))
                .andExpect(model().attributeExists("messages", "listMessagesById"))
                .andExpect(model().attribute("listMessagesById", hasSize(1)))
                .andExpect(model().attribute("listMessagesById", contains(hasToString(feedMessage.toString()))));

        verify(feedService, times(1)).getFeedItemsById(1);
        verifyNoMoreInteractions(feedService);

    }
}
