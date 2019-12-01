package com.sf.votestreams.home

import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class PostRetrofitServiceBuilder {
    @Provides
    fun providesPostRetrofitService(retrofit: Retrofit): PostRetrofitService {
        return retrofit.create<PostRetrofitService>(PostRetrofitService::class.java)
    }

    @Provides
    fun providesRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

@Component(modules = [PostRetrofitServiceBuilder::class])
interface HomeInjector {
    fun injectIntoActivity(app: HomeActivity)
}