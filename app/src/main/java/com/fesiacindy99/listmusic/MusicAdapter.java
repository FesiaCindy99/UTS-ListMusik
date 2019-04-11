package com.fesiacindy99.listmusic;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicAdapterViewHolder> {

    private ArrayList<Music> music;

    public MusicAdapter(ArrayList<Music> list) {
        this.music = list;
    }

    @NonNull
    @Override
    public MusicAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_music, parent, false);
        return new MusicAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapterViewHolder holder, final int position) {
        holder.tfJudul.setText(music.get(position).getJudul());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoveMusicDetail(position, v.getContext());
            }
        });
    }

    private void MoveMusicDetail(int position, Context c) {
        Context context = c;
        Intent i = new Intent(context, detail_Music.class);
        i.putExtra("judul", music.get(position).getJudul());
        i.putExtra("penyanyi", music.get(position).getPenyanyi());
        i.putExtra("rilis", music.get(position).getRilis());
        i.putExtra("pencipta", music.get(position).getPencipta());
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return (music != null) ? music.size() : 0;
    }

    public class MusicAdapterViewHolder extends RecyclerView.ViewHolder {
        private TextView tfJudul;

        public MusicAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tfJudul = (TextView) itemView.findViewById(R.id.tfJudul);
        }
    }
}
