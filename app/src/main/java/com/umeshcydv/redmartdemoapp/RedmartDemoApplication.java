package com.umeshcydv.redmartdemoapp;

import android.app.Application;

import com.umeshcydv.redmartdemoapp.dependencies.ApiComponent;
import com.umeshcydv.redmartdemoapp.dependencies.DaggerApiComponent;
import com.umeshcydv.redmartdemoapp.dependencies.DaggerNetworkComponent;
import com.umeshcydv.redmartdemoapp.dependencies.NetworkComponent;
import com.umeshcydv.redmartdemoapp.dependencies.NetworkModule;

/**
 * Created by umeshcydv on 11/11/17.
 */

public class RedmartDemoApplication extends Application {

    private ApiComponent mApiComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        configureDependencies();
    }

    private void configureDependencies() {
        mApiComponent = DaggerApiComponent.builder()
                .networkComponent(getNetworkComponent())
                .build();
    }

    private NetworkComponent getNetworkComponent() {
        return DaggerNetworkComponent.builder()
                .networkModule(new NetworkModule(Constants.BASE_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return mApiComponent;
    }
}
