
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImReleaseDate {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attributes")
    @Expose
    private ImReleaseDateAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImReleaseDateAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImReleaseDateAttributes attributes) {
        this.attributes = attributes;
    }

}
