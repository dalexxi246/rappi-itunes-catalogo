
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("attributes")
    @Expose
    private IdAttributes attributes;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public IdAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(IdAttributes attributes) {
        this.attributes = attributes;
    }

}
