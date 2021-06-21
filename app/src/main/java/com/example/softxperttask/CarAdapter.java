package com.example.softxperttask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class CarAdapter extends PagedListAdapter<CarItem, CarAdapter.ItemViewHolder> {

    private Context mCtx;

    CarAdapter(Context mCtx) {
        super(DIFF_CALLBACK);
        this.mCtx = mCtx;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.car_item_rv, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        CarItem item = getItem(position);

        if (item != null) {
            holder.brand_tv.setText(item.getBrand());
            if (item.isUsed()) {
                holder.isUsed_tv.setText("Used");
            } else {
                holder.isUsed_tv.setText("New");
            }
            if (item.getConstractionYear() != null) {
                holder.year_tv.setText(item.getConstractionYear());
            } else {
                holder.year_tv.setVisibility(View.GONE);
            }
            Glide.with(mCtx)
                    .load(item.getImageUrl())
                    .centerCrop()
                    .placeholder(R.drawable.image_not_found)
                    .into(holder.carImage);

        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show();
        }
    }

    private static DiffUtil.ItemCallback<CarItem> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<CarItem>() {
                @Override
                public boolean areItemsTheSame(CarItem oldItem, CarItem newItem) {
                    return oldItem.getId() == newItem.getId();
                }

                @Override
                public boolean areContentsTheSame(CarItem oldItem, CarItem newItem) {
                    return oldItem.getBrand().equals(newItem.getBrand());
                }
            };

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView brand_tv;
        TextView year_tv;
        TextView isUsed_tv;
        ImageView carImage;

        public ItemViewHolder(View itemView) {
            super(itemView);
            brand_tv = itemView.findViewById(R.id.brand_tv);
            year_tv = itemView.findViewById(R.id.year_tv);
            isUsed_tv = itemView.findViewById(R.id.isUsed_tv);
            carImage = itemView.findViewById(R.id.carImage);
        }
    }
}