package com.umeshcydv.redmartdemoapp.dependencies;

import com.umeshcydv.redmartdemoapp.services.RedmartAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by umeshcydv on 11/11/17.
 */

@Module
public class ApiModule {

    @Provides
    @CustomScope
    RedmartAPI provideRedmartservice(Retrofit retrofit) {
        return retrofit.create(RedmartAPI.class);
    }
}
