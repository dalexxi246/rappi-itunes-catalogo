
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("attributes")
    @Expose
    private LinkAttributes attributes;

    public LinkAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(LinkAttributes attributes) {
        this.attributes = attributes;
    }

}
