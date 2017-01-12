
package com.grability.rappiitunescatalogo.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImPrice {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attributes")
    @Expose
    private ImPriceAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ImPriceAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImPriceAttributes attributes) {
        this.attributes = attributes;
    }

}
