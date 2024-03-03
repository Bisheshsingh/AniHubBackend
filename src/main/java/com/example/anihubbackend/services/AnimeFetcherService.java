package com.example.anihubbackend.services;

import com.example.anihubbackend.models.AnimeCard;
import com.example.anihubbackend.utils.ElementObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class AnimeFetcherService {
    private static final String DEFAULT_PAGE = "1";

    @Value("${main.url}")
    private String mainUrl;
    @Value("${main.container.name}")
    private String containerName;
    @Value("${main.container.tag}")
    private String containerTag;
    @Autowired
    private ElementObjectMapper elementObjectMapper;

    public List<AnimeCard> getAllAnime(String page) throws IOException {
        return Jsoup.connect(String.format("%s/?page=%s",
                        mainUrl, page
                )).get().getElementsByClass(containerName)
                .first().getElementsByTag(containerTag)
                .stream().map(elementObjectMapper::mapToAnimeCard)
                .toList();
    }

    public List<AnimeCard> getAllAnime() throws IOException {
        return getAllAnime(DEFAULT_PAGE);
    }

    public List<AnimeCard> searchAnime(String name, String page) throws IOException {
        return Jsoup.connect(String.format("%s/search.html?keyword=%s&page=%s",
                        mainUrl, name, page
                )).get().getElementsByClass(containerName)
                .first().getElementsByTag(containerTag)
                .stream().map(elementObjectMapper::mapToAnimeCard)
                .toList();
    }

    public List<AnimeCard> searchAnime(String name) throws IOException {
        return searchAnime(name, DEFAULT_PAGE);
    }
}
