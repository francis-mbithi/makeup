package com.moringaschool.makeups.data.remote;

import com.moringaschool.makeups.data.remote.model.MakeUp;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;



public interface MakeUpProductRestService {

    @GET("http://makeup-api.herokuapp.com/api/v1/products.json?")
    Observable<List<MakeUp>> searchMakeUpProducts(@Query("brand") String brandName, @Query
            ("product_type") String productTypeName);
}
