package com.umeshcydv.redmartdemoapp.presenter;

import com.umeshcydv.redmartdemoapp.models.ListPageInfo;
import com.umeshcydv.redmartdemoapp.models.Product;
import com.umeshcydv.redmartdemoapp.services.RedmartAPI;
import com.umeshcydv.redmartdemoapp.ui.ProductDetailPresentation;
import com.umeshcydv.redmartdemoapp.ui.ProductListPresentation;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import rx.Single;
import rx.SingleSubscriber;

/**
 * Created by umeshcydv on 12/11/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class ProductListpresenterTest {

    @Mock
    ProductListPresentation productListPresentation;

    @Mock
    RedmartAPI redmartAPIServices;


    ProductListPresenter productListPresenter;

    @Before
    public void setUp() {
        productListPresenter = new ProductListPresenter(productListPresentation);
    }

    @Test
    public void testFetchListPageInfo() {
        //todo working on it
        ListPageInfo listPageInfo = new ListPageInfo();
        listPageInfo.setPage(0);
        listPageInfo.setPageSize(20);
        listPageInfo.setOnSaleCount(3000);
        listPageInfo.setTotal(8000);
        listPageInfo.setProducts(new ArrayList<Product>());
        Mockito.when(redmartAPIServices.getListPageInfo(0, 20)).thenReturn(Single.just(listPageInfo));

        productListPresenter.subscribe(redmartAPIServices.getListPageInfo(0, 20), subscriber);

        Mockito.verify(productListPresentation).onSuccess(listPageInfo);
    }

    SingleSubscriber<ListPageInfo> subscriber = new SingleSubscriber<ListPageInfo>() {
        @Override
        public void onSuccess(ListPageInfo listPageInfo) {
            productListPresentation.onSuccess(listPageInfo);
        }

        @Override
        public void onError(Throwable error) {

        }
    };



}
