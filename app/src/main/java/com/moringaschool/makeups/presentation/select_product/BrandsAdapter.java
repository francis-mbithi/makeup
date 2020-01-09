package com.moringaschool.makeups.presentation.select_product;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.makeups.presentation.search.MakeUpSearchActivity;
import com.moringaschool.makeups.R;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.BrandsViewHolder> {
    private List<String> items;

    private BrandsAdapterCallback mBrandsAdapterCallback;

    BrandsAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public BrandsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.brand_item_layout,
                parent, false);

        return new BrandsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(BrandsViewHolder holder, int position) {
        String item = items.get(position);
        Context context = holder.textViewBrands.getContext();
        this.mBrandsAdapterCallback = (BrandsAdapter.BrandsAdapterCallback) holder.textViewBrands
                .getContext();

        holder.textViewBrands.setText(item);

        holder.textViewBrands.setOnClickListener(v -> {
            Intent intent = new Intent(context, MakeUpSearchActivity.class);
            intent.putExtra("product_selected", mBrandsAdapterCallback.getProductName());
            intent.putExtra("brand_selected", item);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class BrandsViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewBrands;

        BrandsViewHolder(View v) {
            super(v);
            textViewBrands = (TextView) v.findViewById(R.id.brand_item);
        }
    }

    public interface BrandsAdapterCallback {
        String getProductName();
    }
}
