package com.trishasofttech.realmdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    Realm realm;
    EditText etname,etemail,etmobile,etaddress;
    Button btnsave,btnview, btnupdate, btndelete;
    TextView tvmsg;
    RecyclerView recyclerView;
    List<MyData> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        etaddress = findViewById(R.id.etaddress);
        btndelete = findViewById(R.id.btndelete);
        etemail  = findViewById(R.id.etemail);
        etmobile = findViewById(R.id.etmobile);
        etname = findViewById(R.id.etname);
        btnsave = findViewById(R.id.btnsave);
        btnview = findViewById(R.id.btnview);
        btnupdate = findViewById(R.id.btnupdate);
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*to initialize the realm db with Activity*/
        Realm.init(this);
        /*to get the instance from Realm object*/
        realm = Realm.getDefaultInstance();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*to create the Realm db*/
                /*to insert the data into realm db in objects form*/
                realm.beginTransaction();
                /*now you can insert the objects*/
                MyData myData = realm.createObject(MyData.class);
                /*pass data into mydata object*/
                myData.setName(etname.getText().toString());
                myData.setEmail(etemail.getText().toString());
                myData.setMobile(etmobile.getText().toString());
                myData.setAddress(etaddress.getText().toString());
                realm.commitTransaction();
                /*clear the form*/
                etname.setText("");
                etemail.setText("");
                etmobile.setText("");
                etaddress.setText("");
            }
        });

        btnview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*to fetch the data from realm database*/
                RealmResults<MyData> realmResults = realm.where(MyData.class).findAll();

                for (MyData myData : realmResults)
                {
                    list.add(new MyData(myData.getName(), myData.getEmail(), myData.getMobile(), myData.getAddress()));
                }

                MyAdapter myAdapter = new MyAdapter(MainActivity.this,list);
                recyclerView.setAdapter(myAdapter);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*change name based on the moblie no*/
                /*fetch record based on the matching keys*/
                RealmResults<MyData> results = realm.where(MyData.class).equalTo("mobile", etmobile.getText().toString()).findAll();
                /*update data based on the results*/
                realm.beginTransaction();

                for(MyData myData : results){
                    myData.setName(etname.getText().toString());
                }

                realm.commitTransaction();

                /*fetch all records and show in recyclerview*/
                /*RealmResults<MyData> realmResults = realm.where(MyData.class).findAll();

                for (MyData myData : realmResults)
                {
                    list.add(new MyData(myData.getName(), myData.getEmail(), myData.getMobile(), myData.getAddress()));
                }

                MyAdapter myAdapter = new MyAdapter(MainActivity.this,list);
                recyclerView.setAdapter(myAdapter);*/
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*delete record based on the moblie no*/
                /*fetch record based on the matching keys*/
                RealmResults<MyData> results = realm.where(MyData.class).equalTo("mobile", etmobile.getText().toString()).findAll();
                /*delete the matching data*/
                realm.beginTransaction();
                // Delete all matches
                results.deleteAllFromRealm();
                realm.commitTransaction();
            }
        });
    }
}