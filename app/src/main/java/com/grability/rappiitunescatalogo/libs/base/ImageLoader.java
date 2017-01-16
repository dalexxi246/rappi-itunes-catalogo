package com.grability.rappiitunescatalogo.libs.base;

import android.widget.ImageView;

/**
 * Created by wilmer on 15/01/17.
 */

public interface ImageLoader {
    void load(ImageView imageView, String URL);

    void setOnFinishedImageLoadingListener(Object listener);
}
