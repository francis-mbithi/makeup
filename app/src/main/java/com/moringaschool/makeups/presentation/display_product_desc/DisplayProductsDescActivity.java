package com.moringaschool.makeups.presentation.display_product_desc;

import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.makeups.R;
import com.moringaschool.makeups.data.remote.model.ProductColor;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DisplayProductsDescActivity extends AppCompatActivity {
    ImageView imageViewToolbar;
    TextView textViewProductTitle;
    TextView textViewProductPrice;
    TextView textViewProductDescription;
    RecyclerView productColorContainer;

    String name;
    String price;
    String imageLink;
    String productLink;
    String description;
    ProductColorAdapter productColorAdapter;
    ArrayList<ProductColor> productColorArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_products_desc);

        imageViewToolbar = (ImageView) findViewById(R.id.toolbar_image);
        textViewProductTitle = (TextView) findViewById(R.id.textView_title);
        textViewProductPrice = (TextView) findViewById(R.id.textView_price);
        textViewProductDescription = (TextView) findViewById(R.id.textView_description);
        productColorContainer = (RecyclerView) findViewById(R.id.product_color_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        retrieveDataFromIntent();
        setToolbarImage();
        setUiText();
        setProductColors();
    }

    private void retrieveDataFromIntent() {
        name = getIntent().getStringExtra("name");

        price = (price == null) ? "No price available" : getIntent().getStringExtra("price");

        imageLink = getIntent().getStringExtra("image_link");
        productLink = getIntent().getStringExtra("product_link");
        description = getIntent().getStringExtra("description");
        productColorArrayList = getIntent().getParcelableArrayListExtra("product_colors");
    }

    private void setToolbarImage() {
        Picasso.get().load(imageLink).into(imageViewToolbar);
    }

    private void setUiText() {
        textViewProductTitle.setText(name);
        textViewProductPrice.setText(price);
        textViewProductDescription.setText(description);
    }

    private void setProductColors() {

        if (productColorArrayList != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false);

            productColorAdapter = new ProductColorAdapter(productColorArrayList);
            productColorContainer.setLayoutManager(linearLayoutManager);
            productColorContainer.setAdapter(productColorAdapter);
            productColorContainer.setVisibility(View.VISIBLE);
        }
    }
}
