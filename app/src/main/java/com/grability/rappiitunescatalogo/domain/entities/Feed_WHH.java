package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.JsonObject;

/**
 * Created by wilmer on 13/01/17.
 */

public class Feed_WHH {

    public JsonObject author;

    @Override
    public String toString() {
        return "Feed_WHH{" +
                "author=" + author.toString() +
                '}';
    }
}
