package com.practice;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by usera on 2017/1/4.
 */

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder> {
    private static final int[] LOGOS = {R.drawable.logo0, R.drawable.logo1, R.drawable.logo2, R.drawable.logo3, R.drawable.logo4, R.drawable.logo5, R.drawable.logo6, R.drawable.logo7, R.drawable.logo8, R.drawable.logo9, R.drawable.logo10, R.drawable.logo11, R.drawable.logo12, R.drawable.logo13};
    MyRecyclerViewAdapterListener listener = null;

    @Override
    public MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyRecyclerViewHolder(view, listener);
    }

    public void setListener(MyRecyclerViewAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position) {
        holder.updateItem(LOGOS[position], "ITEM " + position);
    }

    @Override
    public int getItemCount() {
        return LOGOS.length;
    }

    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        MyRecyclerViewAdapterListener listener = null;
        int imageId;
        String title;
        ImageView itemIv;
        TextView itemTv;

        public MyRecyclerViewHolder(View itemView, MyRecyclerViewAdapterListener listener) {
            super(itemView);
            itemIv = (ImageView) itemView.findViewById(R.id.itemIv);
            itemTv = (TextView) itemView.findViewById(R.id.itemTv);
            this.listener = listener;
            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (MyRecyclerViewHolder.this.listener != null) {
                        int[] coords = new int[2];
                        itemIv.getLocationOnScreen(coords);
                        Rect srcRect = new Rect(coords[0], coords[1], coords[0] + itemIv.getMeasuredWidth(), coords[1] + itemIv.getMeasuredWidth());
                        MyRecyclerViewHolder.this.listener.onItemClick(srcRect, imageId, title);
                    }
                }
            });
        }

        public void updateItem(int imageId, String title) {
            this.imageId = imageId;
            this.title = title;
            itemIv.setImageResource(imageId);
            itemTv.setText(title);
        }
    }

    interface MyRecyclerViewAdapterListener {
        void onItemClick(Rect srcRect, int imageId, String title);
    }
}
