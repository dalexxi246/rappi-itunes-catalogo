
package com.grability.rappiitunescatalogo.domain.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


// TODO: Seguir convirtiendo Attributes___ en sus correspondientes.
public class IdAttributes {

    @SerializedName("im:id")
    @Expose
    private int imId;
    @SerializedName("im:bundleId")
    @Expose
    private String imBundleId;

    public int getImId() {
        return imId;
    }

    public void setImId(int imId) {
        this.imId = imId;
    }

    public String getImBundleId() {
        return imBundleId;
    }

    public void setImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
    }

}
