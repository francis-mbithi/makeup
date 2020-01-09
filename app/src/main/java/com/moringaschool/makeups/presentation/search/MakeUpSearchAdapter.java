package com.moringaschool.makeups.presentation.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.makeups.R;
import com.moringaschool.makeups.data.remote.model.MakeUp;
import com.moringaschool.makeups.data.remote.model.ProductColor;
import com.moringaschool.makeups.presentation.display_product_desc.DisplayProductsDescActivity;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;
import java.util.List;


public class MakeUpSearchAdapter extends RecyclerView.Adapter<MakeUpSearchAdapter
        .MakeUpViewHolder> {

    private List<MakeUp> items;

    MakeUpSearchAdapter(List<MakeUp> items) {
        this.items = items;
    }

    @Override
    public MakeUpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_make_up,
                parent, false);

        return new MakeUpViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MakeUpViewHolder holder, int position) {
        MakeUp item = items.get(position);

        holder.textViewSummary.setText(item.getName());

        Picasso.with(holder.imageViewPreview.getContext()).load(item.getImageLink()).into(holder
                .imageViewPreview);
    }

    @Override
    public int getItemCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    void setItems(List<MakeUp> makeUpList) {
        this.items = makeUpList;
        notifyDataSetChanged();
    }

    class MakeUpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView imageViewPreview;
        final TextView textViewSummary;

        MakeUpViewHolder(View v) {
            super(v);
            imageViewPreview = (ImageView) v.findViewById(R.id.image_view_logo);
            textViewSummary = (TextView) v.findViewById(R.id.text_view_name);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Intent intent = new Intent(v.getContext(), DisplayProductsDescActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("name", items.get(position).getName());
                intent.putExtra("price", items.get(position).getPrice());
                intent.putExtra("image_link", items.get(position).getImageLink());
                intent.putExtra("product_link", items.get(position).getProductLink());
                intent.putExtra("description", items.get(position).getDescription());

                ArrayList<ProductColor> productColorArrayList = new ArrayList<ProductColor>(items
                        .get(position).getProductColors());

                if (!productColorArrayList.isEmpty()) {
                    intent.putParcelableArrayListExtra("product_colors", productColorArrayList);
                }

                v.getContext().startActivity(intent);
            }
        }
    }
}
