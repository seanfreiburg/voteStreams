package com.sf.votestreams.home

import dagger.Component
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class PostRetrofitServiceBuilder {
    @Provides
    fun providesPostRetrofitService(retrofit: Retrofit): PostRetrofitService {
        return retrofit.create<PostRetrofitService>(PostRetrofitService::class.java)
    }

    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

@Module
class SchedulerBuilder {

    @Provides
    @Singleton
    @Named("BACKGROUND")
    fun providesBackgroundScheduler(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @Named("MAIN")
    fun providesMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()

}

@Singleton
@Component(modules = [PostRetrofitServiceBuilder::class, SchedulerBuilder::class])
interface HomeInjector {
    fun injectIntoActivity(app: HomeActivity)
}