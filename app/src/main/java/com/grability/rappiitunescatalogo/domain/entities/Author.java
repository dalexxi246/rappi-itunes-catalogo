
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.SerializedName;

public class Author {

    @SerializedName("name")
    private Name name;

    @SerializedName("uri")
    private Uri uri;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

}
