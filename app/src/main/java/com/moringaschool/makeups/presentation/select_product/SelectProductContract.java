package com.moringaschool.makeups.presentation.select_product;

import com.moringaschool.makeups.presentation.base.MvpPresenter;
import com.moringaschool.makeups.presentation.base.MvpView;


public class SelectProductContract {

    public interface View extends MvpView {

        void setTransition();

        void switchViewsAfterTransition();

        void setProductTextName(String productName);
    }

    interface Presenter extends MvpPresenter<View> {
        void selectProduct();
    }
}
