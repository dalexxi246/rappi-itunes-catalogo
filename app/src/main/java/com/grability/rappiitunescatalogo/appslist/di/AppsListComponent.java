package com.grability.rappiitunescatalogo.appslist.di;

import com.grability.rappiitunescatalogo.RappiItunesAppModule;
import com.grability.rappiitunescatalogo.appslist.view.AppsListFragment;
import com.grability.rappiitunescatalogo.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppsListModule.class, LibsModule.class, RappiItunesAppModule.class})
public interface AppsListComponent {
    void inject(AppsListFragment fragment);
}
