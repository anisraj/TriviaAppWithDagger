package com.anisjamadar26.triviaappdagger.ui.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.anisjamadar26.triviaappdagger.R;
import com.anisjamadar26.triviaappdagger.databinding.DataLayoutItemBinding;
import com.anisjamadar26.triviaappdagger.entity.GameEntity;

import java.util.List;

/**
 * This is adapter for recyclerview, and for its viewholders or item views, I used data binding
 */

public class HistoryDataAdapter extends RecyclerView.Adapter<HistoryDataAdapter.ViewHolder> {
    private final LayoutInflater layoutInflater;
    private List<GameEntity> gameEntities;

    public HistoryDataAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public HistoryDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataLayoutItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.data_layout_item, parent, false);
        return new HistoryDataAdapter.ViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryDataAdapter.ViewHolder holder, int position) {
        if (gameEntities != null) {
            holder.itemBinding.setModel(gameEntities.get(position));
        }
    }

    public void setGameEntities(List<GameEntity> entities) {
        gameEntities = entities;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (gameEntities != null) {
            return gameEntities.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        DataLayoutItemBinding itemBinding;
        public ViewHolder(DataLayoutItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
