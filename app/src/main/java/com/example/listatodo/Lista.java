package com.example.listatodo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Lista extends AppCompatActivity {

    private ListAdapter adapter;
    static private final String TAG = "ToDoList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        ListView listView = (ListView) findViewById(R.id.listView);
        adapter = new ListAdapter(new ArrayList<Item>());
        listView.setAdapter(adapter);

        Button buttonAdd = (Button) findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Lista.this, AddNewItem.class);
                startActivityForResult(intent,1);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode == RESULT_OK){
            Log.i(TAG, "Entered ListActivity.onActivityResult");
            String naziv = data.getStringExtra("name");
            Boolean status = data.getBooleanExtra("status", false);
            String time = data.getStringExtra("time");
            String date = data.getStringExtra("date");

            Item item = new Item();
            item.setNaziv(naziv);
            item.setDate(date);
            item.setStatus(status);
            item.setTime(time);

            adapter.add(item);

        }
    }
}
