package com.grability.rappiitunescatalogo.splash.di;

import com.grability.rappiitunescatalogo.RappiItunesAppModule;
import com.grability.rappiitunescatalogo.libs.di.LibsModule;
import com.grability.rappiitunescatalogo.splash.ui.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {SplashModule.class, LibsModule.class, RappiItunesAppModule.class})
public interface SplashComponent {
    void inject(SplashActivity activity);
}
