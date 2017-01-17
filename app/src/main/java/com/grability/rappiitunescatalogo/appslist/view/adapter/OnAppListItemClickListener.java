package com.grability.rappiitunescatalogo.appslist.view.adapter;

import com.grability.rappiitunescatalogo.model.db.tables.App;

/**
 * Created by wilmer on 16/01/17.
 */

public interface OnAppListItemClickListener {
    void onItemClick(App app);

    void onShowMoreClick();
}
