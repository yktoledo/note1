package com.example.yendry.room1.viewmodel;

import android.arch.lifecycle.LiveData;

import com.example.yendry.room1.base.ViewModelBase;
import com.example.yendry.room1.module.Note;
import com.example.yendry.room1.module.NoteRepository;
import com.example.yendry.room1.view.DetailFragment;

/**
 * Created by yendry on 5/11/18.
 */

public class DetailFragmentViewModel extends ViewModelBase<DetailFragment>{



    public DetailFragmentViewModel(NoteRepository repository) {
        super(repository);

    }

    @Override
    public void init() {
        fragment.showToast();
    }

    public LiveData<Note> getNote(String id){
        return repository.getNote(id);
    }

}
