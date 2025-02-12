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
import com.example.animalhome.entity.AnimalSpecies;

import java.util.ArrayList;
import java.util.List;

public class AnimalSpeciesAdapter extends RecyclerView.Adapter<AnimalSpeciesAdapter.ViewHolder> {

    private final List<AnimalSpecies> list = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animal_species, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnimalSpecies animalSpecies = list.get(position);
        holder.tvAnimalSpecies.setText(animalSpecies.getDesc());
        Glide.with(holder.itemView).load(animalSpecies.getCoverURL()).into(holder.ivAnimalSpecies);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition(), animalSpecies);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<AnimalSpecies> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, AnimalSpecies animalSpecies);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvAnimalSpecies;

        private final ImageView ivAnimalSpecies;

        public ViewHolder(View view) {
            super(view);
            tvAnimalSpecies = view.findViewById(R.id.tv_animal_species);
            ivAnimalSpecies = view.findViewById(R.id.iv_animal_species);
        }
    }

}
