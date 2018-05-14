package com.example.yendry.room1.di;

import com.example.yendry.room1.view.AddFragment;
import com.example.yendry.room1.view.DetailFragment;
import com.example.yendry.room1.view.HomeFragment;

import javax.inject.Singleton;

/**
 * Created by yendry on 5/11/18.
 */
@Singleton
@dagger.Component(modules = {RoomModule.class, ApplicationModule.class})
public interface Component {
    void inject(AddFragment fragment);

    void inject(DetailFragment fragment);

    void inject(HomeFragment fragment);
}
