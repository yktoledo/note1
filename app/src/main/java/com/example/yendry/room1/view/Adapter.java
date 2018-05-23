package com.example.yendry.room1.view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yendry.room1.R;
import com.example.yendry.room1.module.Note;
import com.example.yendry.room1.util.AdapterInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by yendry on 5/11/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyVH> {
    List<Note> list = new ArrayList<>();
    private AdapterInterface listener;
    private Context context;

    public Adapter(AdapterInterface listener, Context context) {
        this.listener = listener;
        this.context = context;
    }


    public void setList(List<Note> list) {
        this.list.clear();
        this.list.addAll(list);
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

    public class MyVH extends RecyclerView.ViewHolder implements View.OnClickListener , View.OnLongClickListener{
        TextView title, content;

        public MyVH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_title_id);
            content = itemView.findViewById(R.id.item_content_id);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(list.get(getAdapterPosition()).getId());
        }

        @Override
        public boolean onLongClick(View v) {
            AlertDialog.Builder alert = new AlertDialog.Builder(
                    context);
            alert.setTitle("Alert!!");
            alert.setMessage("Are you sure to delete contact");
            alert.setPositiveButton("YES", (dialog, which) -> {
                listener.onDelete(list.get(getAdapterPosition()));
                dialog.dismiss();

            });
            alert.setNegativeButton("NO", (dialog, which) -> dialog.dismiss());

            alert.show();
            return true;

        }
    }
}
