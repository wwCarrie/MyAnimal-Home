package com.example.animalhome.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalhome.R;
import com.example.animalhome.entity.VaccineLog;

import java.util.ArrayList;
import java.util.List;

/**
 * 疫苗列表适配器
 */
public class VaccineLogAdapter extends RecyclerView.Adapter<VaccineLogAdapter.ViewHolder> {

    private final List<VaccineLog> list = new ArrayList<>();

    private OnItemDeleteClickListener onItemDeleteClickListener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vaccine, parent, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VaccineLog item = list.get(position);

        holder.tvVaccine.setText(item.getContent());
        holder.tvTime.setText(item.getCreateTime());
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemDeleteClickListener != null) {
                    onItemDeleteClickListener.onDelete(item);
                }
            }
        });
    }

    public void setList(List<VaccineLog> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener onItemDeleteClickListener) {
        this.onItemDeleteClickListener = onItemDeleteClickListener;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addList(List<VaccineLog> list) {
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    /**
     * 添加一条数据
     */
    public void add(VaccineLog vaccineLog) {
        this.list.add(vaccineLog);
        notifyItemInserted(this.list.size() - 1);
    }

    /**
     * 删除一条数据
     */
    public void remove(VaccineLog vaccineLog) {
        int index = this.list.indexOf(vaccineLog);
        this.list.remove(vaccineLog);
        notifyItemRemoved(index);
    }

    public interface OnItemDeleteClickListener {
        void onDelete(VaccineLog item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvVaccine, tvTime, tvDelete;

        public ViewHolder(View view) {
            super(view);
            tvVaccine = view.findViewById(R.id.tv_vaccine);
            tvTime = view.findViewById(R.id.tv_time);
            tvDelete = view.findViewById(R.id.tv_delete);
        }
    }
}
