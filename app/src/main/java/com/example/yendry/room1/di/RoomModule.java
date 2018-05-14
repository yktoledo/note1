/*
 * *
 *  * Copyright (C) 2017 Ryan Kay Open Source Project
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.example.yendry.room1.di;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;

import com.example.yendry.room1.module.NoteDao;
import com.example.yendry.room1.module.NoteDatabase;
import com.example.yendry.room1.module.NoteRepository;
import com.example.yendry.room1.util.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by R_KAY on 8/18/2017.
 */
@Module
public class RoomModule {

    private final NoteDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                NoteDatabase.class,
                "NoteDatabase.db"
        ).build();
    }

    @Provides
    @Singleton
    NoteRepository provideListItemRepository(NoteDao noteDao){
        return new NoteRepository(noteDao);
    }

    @Provides
    @Singleton
    NoteDao provideListItemDao(NoteDatabase database){
        return database.getDao();
    }

    @Provides
    @Singleton
    NoteDatabase provideListItemDatabase(Application application){
        return database;
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory provideViewModelFactory(NoteRepository repository){
        return new CustomViewModelFactory(repository);
    }
}
