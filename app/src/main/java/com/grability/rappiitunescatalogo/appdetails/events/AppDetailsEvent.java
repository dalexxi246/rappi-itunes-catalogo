package com.grability.rappiitunescatalogo.appdetails.events;

import com.grability.rappiitunescatalogo.model.db.tables.App;

/**
 * Created by wilmer on 19/01/17.
 */
public class AppDetailsEvent {
    public static final int READ_EVENT = 246;
    App app;
    int type;
    String errorMsg;

    public App getApp() {
        return app;
    }

    public void setApp(App app) {
        this.app = app;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
