package com.moringaschool.makeups.data;

import com.moringaschool.makeups.data.remote.model.MakeUp;

import java.util.List;

import rx.Observable;



public interface MakeUpRepository {

    Observable<List<MakeUp>> searchMakeUp(String brandName, String productName);
}
