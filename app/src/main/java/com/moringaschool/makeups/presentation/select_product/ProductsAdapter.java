package com.moringaschool.makeups.presentation.select_product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.makeups.R;

import java.util.List;



public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private List<String> items;
    private ProductsAdapterCallback mProductsAdapterCallback;

    ProductsAdapter(List<String> items) {
        this.items = items;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item_layout,
                parent, false);

        return new ProductsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {

        this.mProductsAdapterCallback = (ProductsAdapterCallback) holder.textViewProducts
                .getContext();
        String item = items.get(position);

        holder.textViewProducts.setText(item);

        holder.textViewProducts.setOnClickListener(v -> {
            mProductsAdapterCallback.setTransition();
            mProductsAdapterCallback.switchViewsAfterTransition();
            mProductsAdapterCallback.setProductTextName(item);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        final TextView textViewProducts;

        ProductsViewHolder(View v) {
            super(v);
            textViewProducts = (TextView) v.findViewById(R.id.product_item);
        }
    }

    public interface ProductsAdapterCallback {
        void setTransition();

        void switchViewsAfterTransition();

        void setProductTextName(String productName);
    }
}
