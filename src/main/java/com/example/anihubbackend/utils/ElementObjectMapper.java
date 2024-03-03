package com.example.anihubbackend.utils;

import com.example.anihubbackend.models.AnimeCard;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ElementObjectMapper {
    private static final String A_TAG = "a";

    @Value("${anime.name}")
    private String nameTag;
    @Value("${anime.img}")
    private String imgLinkTag;
    @Value("${anime.updated}")
    private String updated;
    @Value("${main.url}")
    private String mainUrl;

    private String getName(Element element) {
        return element.getElementsByClass(nameTag).text();
    }

    private String getImgLink(Element element) {
        return element.getElementsByClass(imgLinkTag)
                .first().getElementsByTag("img")
                .attr("src");
    }

    private String getTotalEpisodes(Element element) {
       try {
           String[] fullName = element.getElementsByClass(nameTag)
                   .text().split(" ");

           return fullName[fullName.length - 1];
       } catch (ArrayIndexOutOfBoundsException e) {
           return "UNKNOWN";
       }
    }

    private String getLink(Element element) {
        return mainUrl + element.getElementsByTag(A_TAG)
                .attr("href");
    }

    private String getUpdated(Element element) {
        return element.getElementsByClass(updated)
                .text();
    }

    public AnimeCard mapToAnimeCard(Element element) {
        AnimeCard animeCard = new AnimeCard();
        animeCard.setName(getName(element));
        animeCard.setLink(getLink(element));
        animeCard.setUpdated(getUpdated(element));
        animeCard.setImageLink(getImgLink(element));
        animeCard.setTotalEpisodes(getTotalEpisodes(element));

        return animeCard;
    }
}
