package com.moringaschool.makeups.presentation.search;

import com.moringaschool.makeups.data.remote.model.MakeUp;
import com.moringaschool.makeups.presentation.base.MvpPresenter;
import com.moringaschool.makeups.presentation.base.MvpView;

import java.util.List;



public interface MakeUpSearchContract {

    interface View extends MvpView {
        void showSearchResults(List<MakeUp> makeUpList);

        void showEmptyState();

        void showError(String message);

        void showLoading();

        void hideLoading();
    }

    interface Presenter extends MvpPresenter<View> {
        void search(String product, String brand);
    }
}
