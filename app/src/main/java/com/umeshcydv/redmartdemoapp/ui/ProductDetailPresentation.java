package com.umeshcydv.redmartdemoapp.ui;

import com.umeshcydv.redmartdemoapp.models.Product;
import com.umeshcydv.redmartdemoapp.models.ProductDetail;

import rx.Single;

/**
 * Created by umeshcydv on 12/11/17.
 */

public interface ProductDetailPresentation {

    void onSuccess(Product product);

    void onError(String message);

    void switchView();

    Single<ProductDetail> getProductDetail();
}
