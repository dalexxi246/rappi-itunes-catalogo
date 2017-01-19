package com.grability.rappiitunescatalogo.appdetails.di;

import com.grability.rappiitunescatalogo.RappiItunesAppModule;
import com.grability.rappiitunescatalogo.appdetails.view.AppDetailsFragment;
import com.grability.rappiitunescatalogo.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wilmer on 19/01/17.
 */

@Singleton
@Component(modules = {AppDetailsModule.class, LibsModule.class, RappiItunesAppModule.class})
public interface AppDetailsComponent {
    void inject(AppDetailsFragment fragment);
}
