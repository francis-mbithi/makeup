package com.moringaschool.makeups.presentation.select_product;

import android.view.View;

import com.moringaschool.makeups.presentation.base.BasePresenter;


public class SelectProductPresenter extends BasePresenter<SelectProductContract.View> implements
        SelectProductContract.Presenter {

    private android.view.View view;
    private String productName;

    public SelectProductPresenter(View view, String productName) {
        this.view = view;
        this.productName = productName;
    }

    @Override
    public void selectProduct() {
        checkViewAttached();

        getView().setTransition();
        getView().switchViewsAfterTransition();
        getView().setProductTextName(productName);
    }

}
