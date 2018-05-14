package com.example.yendry.room1.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yendry.room1.R;
import com.example.yendry.room1.module.Note;

import java.util.List;

/**
 * Created by yendry on 5/11/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyVH> {
    List<Note> list;

    public void setList(List<Note> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyVH holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        holder.content.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView title, content;

        public MyVH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title_id);
            content = itemView.findViewById(R.id.item_content_id);
        }
    }
}
