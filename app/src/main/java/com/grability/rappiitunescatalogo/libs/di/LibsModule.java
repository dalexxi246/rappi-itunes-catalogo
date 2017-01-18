package com.grability.rappiitunescatalogo.libs.di;

import android.content.Context;

import com.grability.rappiitunescatalogo.libs.GlideImageLoader;
import com.grability.rappiitunescatalogo.libs.GreenRobotEventBus;
import com.grability.rappiitunescatalogo.libs.base.EventBus;
import com.grability.rappiitunescatalogo.libs.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wilmer on 15/01/17.
 */

@Module
public class LibsModule {

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Context context) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (context != null) {
            imageLoader.setLoaderContext(context);
        }
        return imageLoader;
    }

}
