package com.ivann.kezaco;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MediaAdapter  extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    private List<Media> medias;

    public MediaAdapter(List<Media> medias) {
        this.medias = medias;
    }

    @NonNull
    @Override
    public MediaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_media, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MediaAdapter.ViewHolder holder, int position) {
        Media media = medias.get(position);
        holder.theme.setImageResource(UiHelper.drawableIdFromFileName(media.media, holder.itemView.getContext()));
        holder.answer.setText(media.answers.toString());
        holder.difficulty.setText(UiHelper.getMediaDifficulty(media));

    }

    @Override
    public int getItemCount() {
        return medias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView theme;
        final TextView answer;
        final TextView difficulty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            theme = itemView.findViewById(R.id.imageView3);
            answer = itemView.findViewById(R.id.TextViewAnswersContainer);
            difficulty = itemView.findViewById(R.id.textViewDifficulty);
        }
    }
}
