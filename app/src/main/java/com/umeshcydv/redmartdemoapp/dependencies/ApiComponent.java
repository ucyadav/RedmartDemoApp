package com.umeshcydv.redmartdemoapp.dependencies;

import com.umeshcydv.redmartdemoapp.ui.ProductDetailActivity;
import com.umeshcydv.redmartdemoapp.ui.ProductListActivity;

import dagger.Component;

/**
 * Created by umeshcydv on 11/11/17.
 */

@CustomScope
@Component(modules = ApiModule.class, dependencies = NetworkComponent.class)
public interface ApiComponent {

    void inject(ProductListActivity productListActivity);

    void inject(ProductDetailActivity productDetailActivity);
}
