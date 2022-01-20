package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class NewsApiTest {

    NewsApi newsApi;
    
    @Mock
    RequestHeadersSpec rhs;
    
    @Mock
    WebClient webClient;
    
    @Mock
    RequestHeadersUriSpec rhus;
    
    @Mock
    ResponseSpec responseSpec;
    
    @Mock
    Mono<ApiExampleWrapper> mono; 

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	newsApi= new NewsApi();
    	newsApi.setWebClient(webClient);
    }

    @Test
    void itShouldGetNewsStoryByTopic() {
        //given
    	ApiExampleWrapper aWE = new ApiExampleWrapper();
    	Article expectedArticle = new Article();
    	expectedArticle.setTitle("Cows: Nature's Menace?");
    	expectedArticle.setContent("No, they are not.");
    	expectedArticle.setUrl("www.cowtruth.com");
    	List<Article> articles = Collections.singletonList(expectedArticle);
    	aWE.setArticles(articles);
    	when(webClient.get()).thenReturn(rhus);
    	when(rhus.uri((Function<UriBuilder,URI>) any())).thenReturn(rhs);
    	when(rhs.retrieve()).thenReturn(responseSpec);
    	when(responseSpec.bodyToMono(ApiExampleWrapper.class)).thenReturn(mono);
    	when(mono.block()).thenReturn(aWE);
        //when
    	ApiExampleWrapper actualAWE = newsApi.getNewsStoryByTopic("Cows");
        //then
    	verify(webClient, times(1)).get();
    	assertEquals(aWE.getArticles().get(0),expectedArticle);
    }

    @Test
    void itShouldFindStory(){
        //given
    	ApiExampleWrapper aWE = new ApiExampleWrapper();
    	Article expectedArticle = new Article();
    	expectedArticle.setTitle("Cows: Nature's Menace?");
    	expectedArticle.setContent("No, they are not.");
    	expectedArticle.setUrl("www.cowtruth.com");
    	List<Article> articles = Collections.singletonList(expectedArticle);
    	aWE.setArticles(articles);
    	when(webClient.get()).thenReturn(rhus);
    	when(rhus.uri((Function<UriBuilder,URI>) any())).thenReturn(rhs);
    	when(rhs.retrieve()).thenReturn(responseSpec);
    	when(responseSpec.bodyToMono(ApiExampleWrapper.class)).thenReturn(mono);
    	when(mono.block()).thenReturn(aWE);
    	String expectedMessage=
                expectedArticle.getTitle() + " -\n"
                        + expectedArticle.getContent()
                        + "\nFull article: " + expectedArticle.getUrl();
        //when
    	String actualMessage = newsApi.findStory("Cows");
        //then
    	verify(webClient, times(1)).get();
    	assertEquals(expectedMessage, actualMessage);
    }


}