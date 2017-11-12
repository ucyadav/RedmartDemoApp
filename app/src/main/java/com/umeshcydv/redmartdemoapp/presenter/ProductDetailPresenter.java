package com.umeshcydv.redmartdemoapp.presenter;

import com.umeshcydv.redmartdemoapp.models.ProductDetail;
import com.umeshcydv.redmartdemoapp.ui.ProductDetailPresentation;

import rx.SingleSubscriber;

/**
 * Created by umeshcydv on 12/11/17.
 */

public class ProductDetailPresenter extends BasePresenter {

    private ProductDetailPresentation mPresentation;

    public ProductDetailPresenter(ProductDetailPresentation presentation) {
        this.mPresentation = presentation;
    }

    public void fetchProductDetail() {
        subscribe(mPresentation.getProductDetail(), observer);
    }

    SingleSubscriber<ProductDetail> observer = new SingleSubscriber<ProductDetail>() {
        @Override
        public void onSuccess(ProductDetail productDetail) {
            mPresentation.onSuccess(productDetail.getProduct());
        }

        @Override
        public void onError(Throwable error) {
            mPresentation.onError(error.getMessage());
        }
    };
}
