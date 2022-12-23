package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.notes.databinding.ActivityDataInsertBinding;

public class DataInsertActivity extends AppCompatActivity {
  ActivityDataInsertBinding activityDataInsertBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_insert);
        activityDataInsertBinding=ActivityDataInsertBinding.inflate(getLayoutInflater());
        setContentView(activityDataInsertBinding.getRoot());

        String type=getIntent().getStringExtra("type");
         if (type.equals("update")){
            // setTitle("update");
             activityDataInsertBinding.et1.setText(getIntent().getStringExtra("title"));
             activityDataInsertBinding.et2.setText(getIntent().getStringExtra("desc"));
             int id =getIntent().getIntExtra("id",0);
             activityDataInsertBinding.button.setText("Update");
             activityDataInsertBinding.button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent=new Intent();
                     intent.putExtra("title",activityDataInsertBinding.et1.getText().toString());
                     intent.putExtra("desc",activityDataInsertBinding.et2.getText().toString());
                     intent.putExtra("id",id);
                     setResult(RESULT_OK,intent);
                     finish();
                 }
             });
         }
         else{

             setTitle("Add Mode");
             activityDataInsertBinding.button.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     Intent intent=new Intent();
                     intent.putExtra("title",activityDataInsertBinding.et1.getText().toString());
                     intent.putExtra("notes",activityDataInsertBinding.et2.getText().toString());
                     setResult(RESULT_OK,intent);
                     finish();
                 }
             });


         }

    }
     public void onBackPressed(){
        super.onBackPressed();
        startActivity(new Intent(DataInsertActivity.this,MainActivity.class ));
     }

}