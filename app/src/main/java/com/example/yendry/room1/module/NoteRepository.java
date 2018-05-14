package com.example.yendry.room1.module;

import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by yendry on 5/11/18.
 */

public class NoteRepository {

    private final NoteDao noteDao;

    @Inject
    public NoteRepository(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public LiveData<List<Note>> getAllNotes(){
        return noteDao.getAllNotes();
    }

    public LiveData<Note> getNote(String id){
        return noteDao.getNote(id);
    }

    public void deleteote(Note note){
        noteDao.deleteNote(note);
    }
    public void createNote(Note note){
        noteDao.createNote(note);
    }

    public void removeAll(){
        noteDao.removeAll();
    }
}
