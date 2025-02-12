package com.example.animalhome.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animalhome.R;
import com.example.animalhome.entity.AnimalKnowledge;

import java.util.ArrayList;
import java.util.List;

public class AnimalKnowledgeAdapter extends RecyclerView.Adapter<AnimalKnowledgeAdapter.ViewHolder> {

    private final List<AnimalKnowledge> list = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal_knowledge, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnimalKnowledge animalKnowledge = list.get(position);
        holder.tvTitle.setText(animalKnowledge.getTitle());
        holder.tvContent.setText(animalKnowledge.getContent());
        Glide.with(holder.itemView).load(animalKnowledge.getImgResId()).into(holder.ivAnimalKnowledge);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition(), animalKnowledge);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<AnimalKnowledge> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, AnimalKnowledge animalKnowledge);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivAnimalKnowledge;
        private final TextView tvTitle, tvContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnimalKnowledge = itemView.findViewById(R.id.iv_animal_knowledge);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
