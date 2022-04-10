package io.github.aaaamirabbas.edge.hilt

import android.content.Context
import com.aaaamirabbas.reactor.handler.Reactor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.aaaamirabbas.edge.domain.provider.local.ObjectPool
import io.github.aaaamirabbas.edge.utils.moshi.MoshiUtils
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EdgeModule {

    @Singleton
    @Provides
    @Named("AES")
    fun provideReactorAES(context: Context) = Reactor(context, true)

    @Singleton
    @Provides
    @Named("Base64")
    fun provideReactorBase64(context: Context) = Reactor(context, false)

    @Singleton
    @Provides
    fun provideMoshiUtils(moshi: Moshi) = MoshiUtils(moshi)

    @Singleton
    @Provides
    fun provideObjectPool() = ObjectPool()
}