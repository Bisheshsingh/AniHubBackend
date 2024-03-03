package com.example.anihubbackend.models;

import lombok.Data;

@Data
public class AnimeCard {
    private String name;
    private String link;
    private String imageLink;
    private String totalEpisodes;
    private String updated;
}
