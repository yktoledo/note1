package com.example.yendry.room1.base;

import android.arch.lifecycle.ViewModel;

import com.example.yendry.room1.module.NoteRepository;

import javax.inject.Inject;

/**
 * Created by yendry on 5/11/18.
 */

public abstract class ViewModelBase<T extends FragmentBase> extends ViewModel {

   protected T fragment;


    protected NoteRepository repository;

    public ViewModelBase(NoteRepository repository) {
        this.repository = repository;
    }

    public void setFragment(T fragment) {
        this.fragment = fragment;
    }

    public abstract void init();
}
