package com.moringaschool.makeups.presentation.select_product;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.google.firebase.auth.FirebaseAuth;
import com.moringaschool.makeups.LoginActivity;
import com.moringaschool.makeups.R;

import java.util.Arrays;
import java.util.List;

public class SelectProductActivity extends AppCompatActivity implements SelectProductContract
        .View, ProductsAdapter.ProductsAdapterCallback, BrandsAdapter.BrandsAdapterCallback {



    TextView textViewSearchIntro;
    TextView textViewProductName;
    LinearLayout selectProductContainer;
    RecyclerView recyclerViewProducts;
    ProductsAdapter productsAdapter;
    RecyclerView recyclerViewBrands;
    BrandsAdapter brandsAdapter;
    FlexboxLayoutManager brandsLayoutManager;

    String productName = "";

    SelectProductContract.Presenter selectProductPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_product);

        textViewSearchIntro = (TextView) findViewById(R.id.textView_search_intro);
        recyclerViewProducts = (RecyclerView) findViewById(R.id.product_recycler_view);
        selectProductContainer = (LinearLayout) findViewById(R.id.activity_select_product);

        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager();
        setUpFlexboxLayoutManager(layoutManager);

        brandsLayoutManager = new FlexboxLayoutManager();
        setUpFlexboxLayoutManager(brandsLayoutManager);

        List<String> productsList = getItemList(R.array.products_array);
        List<String> brandsList = getItemList(R.array.brands_array);

        productsAdapter = new ProductsAdapter(productsList);
        recyclerViewProducts.setLayoutManager(layoutManager);
        recyclerViewProducts.setAdapter(productsAdapter);

        brandsAdapter = new BrandsAdapter(brandsList);

        selectProductPresenter = new SelectProductPresenter(selectProductContainer, productName);
        selectProductPresenter.attachView(this);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setUpFlexboxLayoutManager(FlexboxLayoutManager layoutManager) {
        layoutManager.setFlexWrap(FlexWrap.WRAP);
        layoutManager.setAlignItems(AlignItems.BASELINE);
        layoutManager.setJustifyContent(JustifyContent.CENTER);
    }

    private List<String> getItemList(int itemId) {
        String[] items = getResources().getStringArray(itemId);
        return Arrays.asList(items);
    }


    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(SelectProductActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void setTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            TransitionManager.beginDelayedTransition(selectProductContainer,
                    new TransitionSet()
                            .addTransition(new Fade())
                            .addTransition(new Slide(Gravity.START)));
        }
    }

    @Override
    public void switchViewsAfterTransition() {
        selectProductContainer.removeView(textViewSearchIntro);
        selectProductContainer.removeView(recyclerViewProducts);
        View child = getLayoutInflater().inflate(R.layout.layout_select_brand, null);
        textViewProductName = (TextView) child.findViewById(R.id.textView_productname);
        recyclerViewBrands = (RecyclerView) child.findViewById(R.id.brand_recycler_view);
        recyclerViewBrands.setLayoutManager(brandsLayoutManager);
        recyclerViewBrands.setAdapter(brandsAdapter);
        selectProductContainer.addView(child);
    }

    @Override
    public void setProductTextName(String productName) {
        this.productName = productName;
        textViewProductName.setText(productName + " from...");
    }

    @Override
    public String getProductName() {
        return productName;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
