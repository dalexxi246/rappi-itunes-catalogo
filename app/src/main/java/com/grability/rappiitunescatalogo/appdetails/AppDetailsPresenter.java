package com.grability.rappiitunescatalogo.appdetails;

import com.grability.rappiitunescatalogo.appdetails.events.AppDetailsEvent;

/**
 * Created by wilmer on 16/01/17.
 */

public interface AppDetailsPresenter {

    void onCreate();

    void onDestroy();

    void getAppInfo(int app_id);

    void onEventMainThreat(AppDetailsEvent evt);

}
