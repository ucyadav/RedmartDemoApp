package com.umeshcydv.redmartdemoapp.services;

import com.umeshcydv.redmartdemoapp.models.ProductDetail;
import com.umeshcydv.redmartdemoapp.models.ListPageInfo;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by umeshcydv on 11/11/17.
 */

public interface RedmartAPI {

    @GET("search")
    Single<ListPageInfo> getListPageInfo(@Query("page") int page, @Query("pageSize") int itemsPerPage);

    @GET("products/{id}")
    Single<ProductDetail> getProductDetail(@Path("id") String productId);
}
