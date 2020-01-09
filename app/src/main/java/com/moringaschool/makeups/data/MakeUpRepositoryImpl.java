package com.moringaschool.makeups.data;

import com.moringaschool.makeups.data.remote.MakeUpProductRestService;
import com.moringaschool.makeups.data.remote.model.MakeUp;

import java.io.IOException;
import java.util.List;

import rx.Observable;



public class MakeUpRepositoryImpl implements MakeUpRepository {

    private MakeUpProductRestService makeUpProductRestService;

    public MakeUpRepositoryImpl(MakeUpProductRestService makeUpProductRestService) {
        this.makeUpProductRestService = makeUpProductRestService;
    }

    @Override
    public Observable<List<MakeUp>> searchMakeUp(final String brandName, final String productName) {
        return Observable.defer(() -> makeUpProductRestService.searchMakeUpProducts(brandName,
                productName))
                .retryWhen(observable -> observable.flatMap(o -> {
                    if (o instanceof IOException) {
                        return Observable.just(null);
                    }
                    return Observable.error(o);
                }));
    }
}
