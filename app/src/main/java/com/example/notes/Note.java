package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    @ColumnInfo(name = "noteTitle")
    private String Title;
    @ColumnInfo(name = "noteDescription")
    private String Description;

    public void setId(int id) {
        Id = id;
    }
     public Note(){

     }

    public int getId() {
        return Id;
    }

    public Note(String title, String description) {
        this.Title = title;
        this.Description = description;

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
