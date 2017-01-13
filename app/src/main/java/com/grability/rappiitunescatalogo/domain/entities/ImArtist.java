
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImArtist {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attributes")
    @Expose
    private ImArtistAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImArtistAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImArtistAttributes attributes) {
        this.attributes = attributes;
    }

}
