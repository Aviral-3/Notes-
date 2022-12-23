package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
  private NoteRepository noteRepository;
  public LiveData<List<Note>>notelist;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository=new NoteRepository(application);
        notelist=noteRepository.getAllData();

    }
    public void Insert(Note note){
        noteRepository.insertData(note);
    }
    public void Delete(Note note){
        noteRepository.deleteData(note);
    }
    public void Update(Note note){
        noteRepository.updateData(note);
    }

    public LiveData<List<Note>>getAllNotes(){
        return  notelist;
    }





}
