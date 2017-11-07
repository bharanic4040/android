package com.example.lavanya.basics;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.lavanya.basics.model.Person;

import java.util.List;

import dao.SampleDatabase;

/**
 * Created by lavanya on 07/11/17.
 */

public class ListActivity extends Activity {


    private SampleDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        db = Room.databaseBuilder(getApplicationContext(),
                SampleDatabase.class, "sample-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();

        Intent intent=getIntent();
        String name=intent.getStringExtra("Name");

        TextView view1=(TextView)findViewById(R.id.textView1);
        view1.setText(fetchAll());


    }

    private String fetchAll(){
        StringBuilder sb=new StringBuilder();
        List<Person> persons=db.daoAccess().fetchAllData();
        for(Person p:persons){
           sb.append(p.toString()+"\n");
           sb.append("-------------------\n");
        }
        return sb.toString();
    }
}
