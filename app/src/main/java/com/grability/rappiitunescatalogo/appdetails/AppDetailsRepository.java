package com.grability.rappiitunescatalogo.appdetails;

import com.grability.rappiitunescatalogo.model.db.tables.App;

/**
 * Created by wilmer on 16/01/17.
 */

public interface AppDetailsRepository {

    void getSavedApps(App a);

}
