package dev.fabirt.melichallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fabirt.melichallenge.data.network.client.KtorHttpClient
import dev.fabirt.melichallenge.data.network.client.MeliApiClient
import dev.fabirt.melichallenge.data.network.service.MeliService
import dev.fabirt.melichallenge.data.network.service.MeliServiceImpl
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = KtorHttpClient.create()

    @Provides
    @Singleton
    fun provideMeliApiClient(client: HttpClient) = MeliApiClient(client)

    @Provides
    @Singleton
    fun provideMeliService(client: MeliApiClient): MeliService = MeliServiceImpl(client)
}