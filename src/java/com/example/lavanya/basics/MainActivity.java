package com.example.lavanya.basics;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lavanya.basics.model.Person;
import com.example.lavanya.basics.service.DownloadService;

import java.util.List;
import dao.SampleDatabase;


public class MainActivity extends Activity {


    private SampleDatabase db;

    private  TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button send=(Button)findViewById(R.id.submit);
        send.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        startDownloadService(view);
                    }
                }
        );
        status=(TextView)findViewById(R.id.name);

        db = Room.databaseBuilder(getApplicationContext(),
                SampleDatabase.class, "sample-db").allowMainThreadQueries().fallbackToDestructiveMigration().build();

    }

    private void startDownloadService(View view){
        Intent intent=new Intent(this, DownloadService.class);
        startService(intent);
        status.setText("Service started >>>");

    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver,new IntentFilter(DownloadService.NOTIFICATION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public void sendMessage(View view){

        EditText name=(EditText)findViewById(R.id.name);
        String nameStr=name.getText().toString();
        EditText salary=(EditText)findViewById(R.id.salary);
        String salaryStr=salary.getText().toString();
        double salaryInt=Double.parseDouble(salaryStr);

        Person person= new Person();
        person.setName(nameStr);
        person.setSalary(salaryInt);
        //savePerson(person);
        Intent intent=new Intent(this,ListViewActivity.class);
        intent.putExtra("Name","Bharani");

       startActivity(intent);

    }

    private void savePerson(Person person){

        db.daoAccess().insertSingle(person);
        Log.d("Inserted Row >> ",person.toString());

    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle=intent.getExtras();
            if(bundle!=null){
                String path=bundle.getString("OUTPUT_PATH");
                String result=bundle.getString("RESULT");

                if(result.equals("0")){
                    Toast.makeText(MainActivity.this,"Downloaded file: "+path,Toast.LENGTH_LONG);
                    status.setText(path);
                }else{
                    Toast.makeText(MainActivity.this,"Error Downloading file !"+path,Toast.LENGTH_LONG);
                    status.setText("Download Error !");
                }


            }

        }
    };
}
