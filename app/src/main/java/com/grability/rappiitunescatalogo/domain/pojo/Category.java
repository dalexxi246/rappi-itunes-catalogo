
package com.grability.rappiitunescatalogo.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("attributes")
    @Expose
    private Attributes______ attributes;

    public Attributes______ getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes______ attributes) {
        this.attributes = attributes;
    }

}
