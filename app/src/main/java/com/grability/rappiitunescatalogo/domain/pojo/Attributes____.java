
package com.grability.rappiitunescatalogo.domain.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


// TODO: Seguir convirtiendo Attributes___ en sus correspondientes.
public class Attributes____ {

    @SerializedName("im:id")
    @Expose
    private String imId;
    @SerializedName("im:bundleId")
    @Expose
    private String imBundleId;

    public String getImId() {
        return imId;
    }

    public void setImId(String imId) {
        this.imId = imId;
    }

    public String getImBundleId() {
        return imBundleId;
    }

    public void setImBundleId(String imBundleId) {
        this.imBundleId = imBundleId;
    }

}
