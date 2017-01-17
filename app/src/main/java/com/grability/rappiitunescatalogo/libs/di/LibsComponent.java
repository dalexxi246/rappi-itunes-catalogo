package com.grability.rappiitunescatalogo.libs.di;

import com.grability.rappiitunescatalogo.RappiItunesAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wilmer on 17/01/17.
 */
@Singleton
@Component(modules = {LibsModule.class, RappiItunesAppModule.class})
public interface LibsComponent {
}
