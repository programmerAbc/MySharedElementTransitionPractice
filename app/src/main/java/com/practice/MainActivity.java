package com.practice;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.MyRecyclerViewAdapterListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView recyclerView;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myRecyclerViewAdapter=new MyRecyclerViewAdapter();
        myRecyclerViewAdapter.setListener(this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myRecyclerViewAdapter);
    }

    @Override
    public void onItemClick(Rect srcRect, int imageId, String title) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("SRC_RECT", srcRect);
        intent.putExtra("IMAGE_ID",imageId);
        intent.putExtra("TITLE",title);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}
