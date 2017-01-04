package com.practice;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Rect;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = SecondActivity.class.getSimpleName();
    ImageView detailIv;
    View detailView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        detailIv = (ImageView) findViewById(R.id.detailIv);
        detailView=findViewById(R.id.detailView);
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
        detailIv.getLocationOnScreen(coords);
        Rect targetRect = new Rect(coords[0], coords[1], coords[0] + detailIv.getMeasuredWidth(), coords[1] +detailIv.getMeasuredHeight());
        detailIv.setPivotX(0);
        detailIv.setPivotY(0);
        detailIv.setTranslationY(srcRect.top - targetRect.top);
        detailIv.setTranslationX(srcRect.left - targetRect.left);
        detailIv.setScaleX((float) srcRect.width() / targetRect.width());
        detailIv.setScaleY((float) srcRect.height() / targetRect.height());
        detailIv.setImageResource(getIntent().getIntExtra("IMAGE_ID",R.mipmap.ic_launcher));
        detailIv.setVisibility(View.VISIBLE);
        PropertyValuesHolder transY = PropertyValuesHolder.ofFloat("translationY", 0f);
        PropertyValuesHolder transX = PropertyValuesHolder.ofFloat("translationX", 0f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(detailIv, transY, transX, scaleX, scaleY).setDuration(900);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.start();
        TransitionDrawable transitionDrawable= (TransitionDrawable) detailView.getBackground();
        transitionDrawable.startTransition(900);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,0);
    }
}
