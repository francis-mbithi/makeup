package com.moringaschool.makeups.presentation.display_product_desc;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.makeups.R;
import com.moringaschool.makeups.data.remote.model.ProductColor;

import java.util.ArrayList;


public class ProductColorAdapter extends RecyclerView.Adapter<ProductColorAdapter
        .ProductColorsViewHolder> {

    private ArrayList<ProductColor> items;

    ProductColorAdapter(ArrayList<ProductColor> items) {
        this.items = items;
    }

    @Override
    public ProductColorsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .product_color_item_layout, parent, false);

        return new ProductColorsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductColorsViewHolder holder, int position) {
        ProductColor item = items.get(position);

        holder.productColorItem.setBackgroundColor(Color.parseColor(item.getHexValue()));
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    class ProductColorsViewHolder extends RecyclerView.ViewHolder {
        final LinearLayout productColorItem;

        ProductColorsViewHolder(View v) {
            super(v);
            productColorItem = (LinearLayout) v.findViewById(R.id.product_color_item);
        }
    }
}
