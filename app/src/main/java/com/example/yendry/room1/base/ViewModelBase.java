package com.example.yendry.room1.base;

import android.arch.lifecycle.ViewModel;

import com.example.yendry.room1.module.NoteRepository;

/**
 * Created by yendry on 5/11/18.
 */

public abstract class ViewModelBase extends ViewModel {
    protected NoteRepository repository;

    public ViewModelBase(NoteRepository repository) {
        this.repository = repository;
    }
}
