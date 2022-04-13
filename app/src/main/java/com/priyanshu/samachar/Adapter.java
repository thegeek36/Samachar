package com.priyanshu.samachar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ModelClass> modelClassesArrayList;

    public Adapter(Context context, ArrayList<ModelClass> modelClassesArrayList) {
        this.context = context;
        this.modelClassesArrayList = modelClassesArrayList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item ,null,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Webview.class);
                intent.putExtra("url", modelClassesArrayList.get(position).getUrl());
                context.startActivity(intent);
                holder.mtime.setText(modelClassesArrayList.get(position).getPublishedAt());
                holder.mauthor.setText(modelClassesArrayList.get(position).getAuthor());
                holder.mheading.setText(modelClassesArrayList.get(position).getTitle());
                holder.mcontent.setText(modelClassesArrayList.get(position).getDescription());
                Glide.with(context).load(modelClassesArrayList.get(position).getUrlToImage()).into(holder.imageView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelClassesArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView mheading,mcontent,mauthor,mtime;
        CardView cardview;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mheading= itemView.findViewById(R.id.main_heading);
            mcontent= itemView.findViewById(R.id.content);
            mauthor= itemView.findViewById(R.id.author);
            mtime= itemView.findViewById(R.id.time);
            imageView= itemView.findViewById(R.id.imageview);
            cardview =itemView.findViewById(R.id.cardview);
        }
    }
}
