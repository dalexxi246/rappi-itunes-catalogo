
package com.grability.rappiitunescatalogo.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImContentType {

    @SerializedName("attributes")
    @Expose
    private ImContentTypeAttributes attributes;

    public ImContentTypeAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(ImContentTypeAttributes attributes) {
        this.attributes = attributes;
    }

}
