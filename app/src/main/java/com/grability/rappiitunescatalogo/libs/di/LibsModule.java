package com.grability.rappiitunescatalogo.libs.di;

import android.app.Activity;

import com.grability.rappiitunescatalogo.libs.GlideImageLoader;
import com.grability.rappiitunescatalogo.libs.GreenRobotEventBus;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by wilmer on 15/01/17.
 */

public class LibsModule {

    Activity activity;

    public LibsModule() {
    }

    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus provideEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Activity activity) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (activity != null) {
            imageLoader.setLoaderContext(activity);
        }
        return imageLoader;
    }

    @Provides
    @Singleton
    Activity provideActivity() {
        return this.activity;
    }


}
