package com.example.yendry.room1.util;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.yendry.room1.base.ViewModelBase;
import com.example.yendry.room1.module.NoteRepository;
import com.example.yendry.room1.view.DetailFragment;
import com.example.yendry.room1.viewmodel.AddFragmentViewModel;
import com.example.yendry.room1.viewmodel.DetailFragmentViewModel;
import com.example.yendry.room1.viewmodel.HomeFragmentViewModel;

import javax.inject.Inject;

/**
 * Created by yendry on 5/11/18.
 */

public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final NoteRepository repository;

    @Inject
    public CustomViewModelFactory(NoteRepository repository) {
        this.repository = repository;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddFragmentViewModel.class))
            return (T) new AddFragmentViewModel(repository);
        else if (modelClass.isAssignableFrom(DetailFragmentViewModel.class))
            return (T) new DetailFragmentViewModel(repository);
        else if (modelClass.isAssignableFrom(HomeFragmentViewModel.class))
            return (T) new HomeFragmentViewModel(repository);

        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
