package com.example.yendry.room1.module;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by yendry on 5/11/18.
 */
@Dao
public interface NoteDao {


    @Query("SELECT * FROM Note")
    LiveData<List<Note>> getAllNotes();

    @Query("SELECT * FROM Note WHERE id=:id")
    LiveData<Note> getNote(String id);

    @Insert(onConflict = REPLACE)
    void createNote(Note note);

    @Delete
    void deleteNote(Note note);
    @Query("delete  from Note ")
    void removeAll();
}
