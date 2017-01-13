
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeedLink {

    @SerializedName("attributes")
    @Expose
    private FeedLinkAttributes attributes;

    public FeedLinkAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(FeedLinkAttributes attributes) {
        this.attributes = attributes;
    }

}
