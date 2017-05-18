package com.example.mohamed.advanceadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.example.mohamed.advanceadapter.listener.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AAdapter aAdapter = new AAdapter(new TestAdapter());
        aAdapter.addFooter(LayoutInflater.from(this).inflate(R.layout.footer, null));
        aAdapter.addHeader(LayoutInflater.from(this).inflate(R.layout.header, null));
        aAdapter.addHeader(LayoutInflater.from(this).inflate(R.layout.header1, null));
        aAdapter.addHeader(LayoutInflater.from(this).inflate(R.layout.header2, null));
        aAdapter.addHeader(LayoutInflater.from(this).inflate(R.layout.header3, null));

        aAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, boolean isHeader, boolean isFooter, int hfPosition, int position) {

            }
        });

        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(aAdapter);
    }
}
