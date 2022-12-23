package com.example.notes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.notes.databinding.EachnotesBinding;

public class RVAdapter extends ListAdapter<Note,RVAdapter.viewHolder> {

    public RVAdapter(MainActivity mainActivity){
     super(CALLBACK);
    }
  private static final DiffUtil.ItemCallback<Note>CALLBACK=new DiffUtil.ItemCallback<Note>() {
      @Override
      public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
          return oldItem.getId()==newItem.getId();
      }

      @Override
      public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
          return oldItem.getTitle().equals(newItem.getTitle())&& oldItem.getDescription().equals(newItem.getDescription());
      }
  };
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.eachnotes,parent,false);
        return new viewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
     Note note= getItem(position);
     holder.eachnotesBinding.textView2.setText(note.getTitle());
     holder.eachnotesBinding.textView4.setText(note.getDescription());
    }
    public Note getNote(int positions ){
        return getItem(positions);
    }


    public class viewHolder extends ViewHolder{
        EachnotesBinding eachnotesBinding;
        public viewHolder(@NonNull View itemView){
            super(itemView);
            eachnotesBinding=EachnotesBinding.bind(itemView);
        }

    }
}
