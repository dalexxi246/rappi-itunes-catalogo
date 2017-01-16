package com.grability.rappiitunescatalogo.libs.base;

/**
 * Created by wilmer on 15/01/17.
 */

public interface EventBus {
    void register(Object subscriber);

    void unregister(Object subscriber);

    void post(Object event);
}
