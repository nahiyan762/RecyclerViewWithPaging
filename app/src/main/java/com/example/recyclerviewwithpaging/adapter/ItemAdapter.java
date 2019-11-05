package com.example.recyclerviewwithpaging.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerviewwithpaging.R;
import com.example.recyclerviewwithpaging.datasource.NetworkState;
import com.example.recyclerviewwithpaging.model.Item;

public class ItemAdapter extends PagedListAdapter<Item, RecyclerView.ViewHolder > {

    private Context context;
    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    public ItemAdapter(Context context) {
        super(ItemDiffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_PROGRESS) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_network_state, parent, false);
            return new NetworkViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
            return new ItemViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            Item item = getItem(position);
            if (item != null){
                ((ItemViewHolder) holder).userName.setText(item.getOwner().getDisplay_name());
                Glide.with(context)
                    .load(item.getOwner().getProfile_image())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(((ItemViewHolder) holder).userAvatar);
        }
        } else if (holder instanceof NetworkViewHolder) {
            ((NetworkViewHolder) holder).loadingProgressBar.setVisibility(View.VISIBLE);
        }
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView userAvatar;
        private TextView userName;

         ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            userAvatar = itemView.findViewById(R.id.UserAvatar);
            userName = itemView.findViewById(R.id.UserName);

        }
    }

    class NetworkViewHolder extends RecyclerView.ViewHolder {

        private ProgressBar loadingProgressBar;

        NetworkViewHolder(@NonNull View itemView) {
            super(itemView);
            loadingProgressBar = itemView.findViewById(R.id.loadingProgressBar);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    private boolean hasExtraRow() {
        if (NetworkState.getInstance().getStatus().equals("LOADED") || NetworkState.getInstance().getStatus().equals("FAILED")) {
            return true;
        } else {
            return false;
        }
    }

    private static DiffUtil.ItemCallback<Item> ItemDiffCallback = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.getAnswer_id().equals(newItem.getAnswer_id());
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Item oldItem, @NonNull Item newItem) {
            return oldItem.equals(newItem);
        }
    };
}
