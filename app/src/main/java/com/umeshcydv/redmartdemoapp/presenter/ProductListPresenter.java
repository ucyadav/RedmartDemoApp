package com.umeshcydv.redmartdemoapp.presenter;

import com.umeshcydv.redmartdemoapp.models.ListPageInfo;
import com.umeshcydv.redmartdemoapp.ui.ProductListPresentation;
import rx.SingleSubscriber;

/**
 * Created by umeshcydv on 11/11/17.
 */

public class ProductListPresenter extends BasePresenter {

    private ProductListPresentation mProductListPresentation;
    private int totalProductCount;
    private int currentProductCount;
    private int pageCount;
    private int pageSize = 20;
    private boolean shouldFetchMore = true;

    public ProductListPresenter(ProductListPresentation productListPresentation) {
        mProductListPresentation = productListPresentation;
    }

    public void fetchListPageInfo() {
        subscribe(mProductListPresentation.getListPageinfo(), subscriber);
        mProductListPresentation.showProgressBar();
    }

    public void fetchMoreProducts() {
        if (currentProductCount < totalProductCount) {
            this.pageCount ++;
            fetchListPageInfo();
            shouldFetchMore = false;
        } else {
            shouldFetchMore = false;
        }
    }

    SingleSubscriber<ListPageInfo> subscriber = new SingleSubscriber<ListPageInfo>() {
        @Override
        public void onSuccess(ListPageInfo listPageInfo) {
            totalProductCount = listPageInfo.getTotal();
            pageCount = listPageInfo.getPage();
            if (listPageInfo.getProducts().size() > 0) {
                currentProductCount = listPageInfo.getProducts().get(listPageInfo.getProducts().size() - 1).getPr();
            }
            mProductListPresentation.onSuccess(listPageInfo);
            mProductListPresentation.hideProgressBar();
            shouldFetchMore = true;
        }

        @Override
        public void onError(Throwable error) {
            mProductListPresentation.onError(error.getMessage());
        }
    };

    private SingleSubscriber<ListPageInfo> getSubscriber() {
        return new SingleSubscriber<ListPageInfo>() {
            @Override
            public void onSuccess(ListPageInfo listPageInfo) {
                totalProductCount = listPageInfo.getTotal();
                pageCount = listPageInfo.getPage();
                if (listPageInfo.getProducts().size() > 0) {
                    currentProductCount = listPageInfo.getProducts().get(listPageInfo.getProducts().size() - 1).getPr();
                }
                mProductListPresentation.onSuccess(listPageInfo);
                mProductListPresentation.hideProgressBar();
                shouldFetchMore = true;
            }

            @Override
            public void onError(Throwable error) {
                mProductListPresentation.onError(error.getMessage());
            }
        };
    }

    public int getPageCount() {
        return this.pageCount;
    }

    public int getPageSize() {
       return this.pageSize;
    }

    public int getCurrentProductCount() {
        return this.currentProductCount;
    }

    public boolean getShouldFetchMore() {
        return shouldFetchMore;
    }
}
