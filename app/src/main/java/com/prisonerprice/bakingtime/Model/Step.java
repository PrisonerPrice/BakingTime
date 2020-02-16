package com.prisonerprice.bakingtime.Model;

public class Step {

    int id;
    String shortDescription;
    String description;
    String videoUrl;
    String thumbnailUrl;

    public Step(int id, String shortDescription, String description, String videoUrl, String thumbnailUrl) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
    }
}
