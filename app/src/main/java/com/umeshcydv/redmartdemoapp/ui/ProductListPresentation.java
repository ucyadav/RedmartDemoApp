package com.umeshcydv.redmartdemoapp.ui;

import com.umeshcydv.redmartdemoapp.models.ListPageInfo;


import rx.Observable;
import rx.Single;

/**
 * Created by umeshcydv on 11/11/17.
 */

public interface ProductListPresentation {

    void onSuccess(ListPageInfo listPageInfo);

    void onError(String message);

    void showProgressBar();

    void hideProgressBar();

    Single<ListPageInfo> getListPageinfo();
}
