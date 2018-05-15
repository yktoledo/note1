package com.example.yendry.room1.viewmodel;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Handler;

import com.example.yendry.room1.base.ViewModelBase;
import com.example.yendry.room1.module.Note;
import com.example.yendry.room1.module.NoteRepository;
import com.example.yendry.room1.view.AddFragment;

/**
 * Created by yendry on 5/11/18.
 */

public class AddFragmentViewModel extends ViewModelBase<AddFragment>{


    public AddFragmentViewModel(NoteRepository repository) {
        super(repository);
    }

    @Override
    public void init() {
        fragment.showToast();
    }


    public void insert(Note note) {

        new AddItemTask().execute(note);
    }

    @SuppressLint("StaticFieldLeak")
    private class AddItemTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... item) {
            repository.createNote(item[0]);
            return null;
        }
    }


}
