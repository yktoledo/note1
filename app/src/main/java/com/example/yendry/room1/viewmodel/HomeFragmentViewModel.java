package com.example.yendry.room1.viewmodel;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.example.yendry.room1.base.ViewModelBase;
import com.example.yendry.room1.module.Note;
import com.example.yendry.room1.module.NoteRepository;

import java.util.List;

/**
 * Created by yendry on 5/11/18.
 */

public class HomeFragmentViewModel extends ViewModelBase {



    public HomeFragmentViewModel(NoteRepository repository) {
        super(repository);
    }


    public LiveData<List<Note>> getAllNotes() {
        return repository.getAllNotes();
    }


    public void removeAll() {
        new RemoveAllTask().execute();
    }

    public void deleteNote(Note note) {
        new RemoveItemTask().execute(note);
    }

    @SuppressLint("StaticFieldLeak")
    private class RemoveItemTask extends AsyncTask<Note, Void, Void> {

        @Override
        protected Void doInBackground(Note... item) {
            repository.deleteote(item[0]);
            return null;
        }
    }

    @SuppressLint("StaticFieldLeak")
    private class RemoveAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... item) {
            repository.removeAll();
            return null;
        }
    }
}
