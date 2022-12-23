package com.example.notes;

import static androidx.lifecycle.ViewModelProvider.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import com.example.notes.databinding.ActivityMainBinding;
import java.util.List;
public class MainActivity extends AppCompatActivity {
 ActivityMainBinding activityMainBinding;
 private NoteViewModel noteViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       activityMainBinding=ActivityMainBinding.inflate(getLayoutInflater());
       setContentView(activityMainBinding.getRoot());
        noteViewModel = new ViewModelProvider(this). get(NoteViewModel.class);
        activityMainBinding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(MainActivity.this,DataInsertActivity.class);
             intent.putExtra("type","add mode");
               startActivityForResult(intent,1);
           }
       });
        activityMainBinding.Rv.setLayoutManager(new LinearLayoutManager(this));
        activityMainBinding.Rv.setHasFixedSize(true);
        RVAdapter adapter=new RVAdapter(MainActivity.this);
        activityMainBinding.Rv.setAdapter(adapter);
        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.submitList( notes);
            }
        });
       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
        @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if(direction == ItemTouchHelper.LEFT){
                    noteViewModel.Delete(adapter.getNote (viewHolder.getAdapterPosition()));
                    Toast.makeText(MainActivity.this, "Notes Deleted ", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent= new Intent(MainActivity.this, DataInsertActivity.class);
                  intent.putExtra("type","update");
                    intent.putExtra("title", adapter.getNote(viewHolder.getAdapterPosition()).getTitle());
                    intent.putExtra("desc", adapter.getNote(viewHolder.getAdapterPosition()).getDescription());
                    intent.putExtra("id", adapter.getNote(viewHolder.getAdapterPosition()).getId());
                     startActivityForResult(intent,2);
                }
            }
        }).attachToRecyclerView(activityMainBinding.Rv);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            String title =data.getStringExtra("title");
            String desc =data.getStringExtra("notes");
            Note note= new Note(title,desc);
            noteViewModel.Insert(note );
            Toast.makeText(this, "WORKING!!", Toast.LENGTH_SHORT).show();
        }
        else if(requestCode==2){
            String title =data.getStringExtra("title");
            String desc =data.getStringExtra("desc");
            Note note= new Note(title,desc);
            note.setId(data.getIntExtra("id",0));
            noteViewModel.Update(note);
            Toast.makeText(MainActivity.this, "Notes Updated", Toast.LENGTH_SHORT).show();
        }
    }
}