package com.example.tasklist;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> items;
    private ListView list;
    private Button button;
    private ArrayAdapter<String> itemsAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=findViewById(R.id.list);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                additem(view);

            }
        });
        items= new ArrayList<>();
        itemsAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        list.setAdapter(itemsAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                remove(position);
            }
        });


    }
    private boolean remove(int position){
        Context context = getApplicationContext();
        Toast.makeText(context, "Item removed", Toast.LENGTH_LONG).show();
        items.remove(position);
        itemsAdapter.notifyDataSetChanged();
        return true;

    }

    private void additem(View view) {
        EditText input= findViewById(R.id.edit_text);
        String itemText= input.getText().toString().trim();

        if(!(itemText.isEmpty())){
            itemsAdapter.add(itemText);
            input.setText("");
            input.setTextColor(Color.BLACK);
        }

        else{
            Toast.makeText(getApplicationContext(), "Please enter a task.", Toast.LENGTH_LONG).show();
        }

    }
}