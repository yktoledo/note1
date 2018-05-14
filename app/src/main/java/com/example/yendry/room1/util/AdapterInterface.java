package com.example.yendry.room1.util;

import com.example.yendry.room1.module.Note;

public interface AdapterInterface {
    void onItemClick(String id);

    void onDelete(Note note);
}
