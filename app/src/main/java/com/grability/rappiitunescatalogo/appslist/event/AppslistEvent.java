package com.grability.rappiitunescatalogo.appslist.event;

import com.grability.rappiitunescatalogo.model.db.tables.App;

import java.util.List;

/**
 * Created by wilmer on 15/01/17.
 */
public class AppslistEvent {

    public static final int UPDATE_EVENT = 373;
    public static final int READ_EVENT = 192;

    int type;
    List<App> apps;
    String errorMsg;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
