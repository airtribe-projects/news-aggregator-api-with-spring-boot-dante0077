package com.news.aggregator.service;

import com.news.aggregator.dto.NewsApiResponse;
import com.news.aggregator.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {

    @Value("${news.api.key}")
    private String apiKey;

//    private static final String API_KEY = apiKey; // Replace with real key
    private static final String NEWS_API_URL = "https://newsapi.org";

    @Autowired
    private WebClient webClient;

    public List<Article> fetchNewsForPreferences(List<String> preferences) {
        List<Article> articles = new ArrayList<>();

        for (String preference : preferences) {
            try {
                System.out.println("Fetching news for preference: " + preference);
                NewsApiResponse response = webClient.get()
                        .uri(uriBuilder -> uriBuilder
                                .scheme("https")
                                .host("newsapi.org")
                                .path("/v2/top-headlines")
                                .queryParam("category", preference)
                                .queryParam("language", "en")
                                .queryParam("apiKey", apiKey)
                                .build())
                        .retrieve()
                        .bodyToMono(NewsApiResponse.class)
                        .block();

                if (response != null && response.getArticles() != null) {
                    articles.addAll(response.getArticles());
                }

            } catch (Exception e) {
                System.out.println(e);
                System.err.println("Error fetching news for preference: " + preference);
            }
        }

        return articles;
    }
}
