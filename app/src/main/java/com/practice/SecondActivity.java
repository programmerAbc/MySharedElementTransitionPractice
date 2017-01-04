package com.practice;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Rect;
import android.graphics.drawable.TransitionDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    ImageButton seBtn;
    View rootRelativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        seBtn = (ImageButton) findViewById(R.id.seBtn);
        rootRelativeLayout=findViewById(R.id.rootRelativeLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Rect srcRect = getIntent().getParcelableExtra("SRC_RECT");
        int[] coords = new int[2];
        seBtn.getLocationOnScreen(coords);
        Rect targetRect = new Rect(coords[0], coords[1], coords[0] + seBtn.getMeasuredWidth(), coords[1] + seBtn.getMeasuredHeight());
        seBtn.setTranslationY(srcRect.top - targetRect.top);
        seBtn.setTranslationX(srcRect.left - targetRect.left);
        seBtn.setScaleX((float) srcRect.width() / targetRect.width());
        seBtn.setScaleY((float) srcRect.height() / targetRect.height());
        seBtn.setVisibility(View.VISIBLE);
        seBtn.setPivotX(0);
        seBtn.setPivotY(0);
        PropertyValuesHolder transY = PropertyValuesHolder.ofFloat("translationY", 0f);
        PropertyValuesHolder transX = PropertyValuesHolder.ofFloat("translationX", 0f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(seBtn, transY, transX, scaleX, scaleY).setDuration(900);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
        TransitionDrawable transitionDrawable= (TransitionDrawable) rootRelativeLayout.getBackground();
        transitionDrawable.startTransition(900);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
}
