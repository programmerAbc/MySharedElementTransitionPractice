package com.practice;

import android.content.Intent;
import android.graphics.Rect;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    ImageButton seBtn;
    RelativeLayout rootRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootRelativeLayout= (RelativeLayout) findViewById(R.id.rootRelativeLayout);
        seBtn = (ImageButton) findViewById(R.id.seBtn);
        seBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] coords = new int[2];
                seBtn.getLocationOnScreen(coords);
                Log.e(TAG, "src location:left=" + coords[0] + ",right=" + coords[1]);
                Rect srcRect = new Rect(coords[0], coords[1], coords[0] + seBtn.getMeasuredWidth(), coords[1] + seBtn.getMeasuredWidth());
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("SRC_RECT", srcRect);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                seBtn.setX(event.getX());
                seBtn.setY(event.getY());
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
