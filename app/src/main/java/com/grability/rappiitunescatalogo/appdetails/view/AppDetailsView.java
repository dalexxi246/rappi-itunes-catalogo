package com.grability.rappiitunescatalogo.appdetails.view;

import com.grability.rappiitunescatalogo.model.db.tables.App;

/**
 * Created by wilmer on 19/01/17.
 */

public interface AppDetailsView {

    void getAppDetails(int appId);

    void onAppLoaded(App app);

    void onError(String errorMsg);

}
