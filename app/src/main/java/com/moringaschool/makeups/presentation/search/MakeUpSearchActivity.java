package com.moringaschool.makeups.presentation.search;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.airbnb.lottie.LottieAnimationView;
import com.moringaschool.makeups.R;
import com.moringaschool.makeups.data.remote.model.MakeUp;
import com.moringaschool.makeups.injection.Injection;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MakeUpSearchActivity extends AppCompatActivity implements MakeUpSearchContract.View {

    private ProgressBar progressBar;
    private RecyclerView recyclerViewMakeUp;
    private TextView textViewErrorMessage;
    private TextView textViewEmptyMessage;
    private TextView textViewGoBackMessage;
    private LottieAnimationView lottieAnimationView;
    private MakeUpSearchAdapter makeUpSearchAdapter;

    private MakeUpSearchContract.Presenter makeupSearchPresenter;
    private static final int CAMERA_REQUEST=1888;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up_search);
        imageView=(ImageView) this.findViewById(R.id.imageView1);
        Button photoButton=(Button) this.findViewById(R.id.button1);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        });




        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        textViewErrorMessage = (TextView) findViewById(R.id.text_view_error_msg);
        textViewEmptyMessage = (TextView) findViewById(R.id.textView_empty_in_here);
        textViewGoBackMessage = (TextView) findViewById(R.id.textView_go_back_and_search);
        recyclerViewMakeUp = (RecyclerView) findViewById(R.id.recycler_view_makeup);
        lottieAnimationView = (LottieAnimationView) findViewById(R.id.animation_view);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL);


        makeUpSearchAdapter = new MakeUpSearchAdapter(null);
        recyclerViewMakeUp.setLayoutManager(staggeredGridLayoutManager);
        recyclerViewMakeUp.setAdapter(makeUpSearchAdapter);

        makeupSearchPresenter = new MakeUpSearchPresenter(Injection.provideUserRepo(), Schedulers.io(),
                AndroidSchedulers.mainThread());
        makeupSearchPresenter.attachView(this);

        String brand = getIntent().getStringExtra("brand_selected");
        String product = getIntent().getStringExtra("product_selected");

        makeupSearchPresenter.search(brand, product);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }









    @Override
    protected void onDestroy() {
        super.onDestroy();
        makeupSearchPresenter.detachView();
    }

    @Override
    public void showSearchResults(List<MakeUp> makeUpList) {
        textViewErrorMessage.setVisibility(View.GONE);
        recyclerViewMakeUp.setVisibility(View.VISIBLE);
        makeUpSearchAdapter.setItems(makeUpList);
    }

    @Override
    public void showError(String message) {
        textViewErrorMessage.setVisibility(View.VISIBLE);
        recyclerViewMakeUp.setVisibility(View.GONE);
        textViewErrorMessage.setText(message);
    }

    @Override
    public void showEmptyState() {
        textViewErrorMessage.setVisibility(View.GONE);
        recyclerViewMakeUp.setVisibility(View.GONE);
        lottieAnimationView.setVisibility(View.VISIBLE);
        textViewEmptyMessage.setVisibility(View.VISIBLE);
        textViewGoBackMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerViewMakeUp.setVisibility(View.GONE);
        textViewErrorMessage.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerViewMakeUp.setVisibility(View.VISIBLE);
        textViewErrorMessage.setVisibility(View.GONE);
    }


}
