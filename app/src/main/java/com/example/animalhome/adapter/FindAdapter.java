package com.example.animalhome.adapter;

import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.animalhome.R;
import com.example.animalhome.db.BusinessResult;
import com.example.animalhome.db.FavoriteDB;
import com.example.animalhome.entity.Find;
import com.example.animalhome.utils.CurrentUserUtils;

import java.util.ArrayList;
import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {

    private final List<Find> list = new ArrayList<>();

    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Find item = list.get(position);

        Glide.with(holder.itemView).load(item.getUrl()).into(holder.iv);
        holder.tvTitle.setText(item.getTitle());
        if (item.getFavorite()) {
            holder.ivFavorite.setImageTintList(ColorStateList.valueOf(holder.itemView.getContext().getColor(R.color.main)));
        } else {
            holder.ivFavorite.setImageTintList(ColorStateList.valueOf(holder.itemView.getContext().getColor(R.color.hint)));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.getAdapterPosition(), item);
                }
            }
        });

        holder.ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getFavorite()) {
                    //取消收藏
                    BusinessResult<Void> result = FavoriteDB.unlike(CurrentUserUtils.getCurrentUser().getId(), item.getId());
                    Toast.makeText(holder.itemView.getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                    if (!result.isSuccess()) {
                        return;
                    }
                    item.setFavorite(false);
                    holder.ivFavorite.setImageTintList(ColorStateList.valueOf(holder.itemView.getContext().getColor(R.color.hint)));
                    if (onItemClickListener != null) {
                        onItemClickListener.onUnLike(holder.getAdapterPosition(), item);
                    }
                } else {
                    //收藏
                    BusinessResult<Void> result = FavoriteDB.like(CurrentUserUtils.getCurrentUser().getId(), item.getId());
                    Toast.makeText(holder.itemView.getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                    if (!result.isSuccess()) {
                        return;
                    }
                    item.setFavorite(true);
                    holder.ivFavorite.setImageTintList(ColorStateList.valueOf(holder.itemView.getContext().getColor(R.color.main)));
                }
            }
        });
    }

    public void setList(List<Find> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<Find> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public interface OnItemClickListener {
        void onItemClick(int position, Find item);
        void onUnLike(int position, Find item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;

        private final ImageView iv, ivFavorite;

        public ViewHolder(View view) {
            super(view);
            tvTitle = view.findViewById(R.id.tv_title);
            iv = view.findViewById(R.id.iv);
            ivFavorite = view.findViewById(R.id.iv_favorite);
        }

    }
}
