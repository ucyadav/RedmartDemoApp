package com.umeshcydv.redmartdemoapp.dependencies;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by umeshcydv on 11/11/17.
 */


@Singleton
@Component(modules = NetworkModule.class)
public interface NetworkComponent {

    Retrofit getRetrofit();

}
