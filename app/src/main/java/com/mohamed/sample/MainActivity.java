package com.mohamed.sample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.mohamed.aadapter.AAdapter;
import com.mohamed.aadapter.listener.OnItemClickListener;


@SuppressWarnings("unused")
public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private AAdapter aAdapter;

    private RecyclerView recycler;
    private View footer;
    private View header;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = (RecyclerView) findViewById(R.id.recycler);

        footer = LayoutInflater.from(this).inflate(R.layout.footer, null);
        header = LayoutInflater.from(this).inflate(R.layout.header, null);

        aAdapter = new AAdapter(new TestAdapter());
        aAdapter.addFooter(footer);
        aAdapter.addHeader(header);

        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(aAdapter);
    }

    @Override
    public void onItemClick(View view, boolean isHeader, boolean isFooter, int hfPosition, int position) {
        Toast.makeText(MainActivity.this,
                "isHeader: " + isHeader + ", isFooter: " + isFooter + ", hfPosition: " + hfPosition + ", position: " + position,
                Toast.LENGTH_SHORT).show();
    }

    public void removeHeader() {
        aAdapter.removeHeader(header);
    }

    public void removeFooter() {
        aAdapter.removeFooter(footer);
    }

    public int getFooterCount() {
        return aAdapter.getFootersCount();
    }

    public int getHeaderCount() {
        return aAdapter.getHeadersCount();
    }

    public boolean hasFooters() {
        return aAdapter.hasFooters();
    }

    public boolean hasHeaders() {
        return aAdapter.hasHeaders();
    }

    public void removeAllHeaders() {
        aAdapter.removeAllHeaders();
    }

    public void removeAllFooters() {
        aAdapter.removeAllFooters();
    }

    public void removeOnItemClickListener() {
        aAdapter.removeOnItemClickListener();
    }

    public void setOnItemClickListener() {
        aAdapter.setOnItemClickListener(recycler, this);
    }
}
