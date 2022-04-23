package dev.fabirt.melichallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fabirt.melichallenge.data.network.service.MeliService
import dev.fabirt.melichallenge.domain.repository.MeliRepository
import dev.fabirt.melichallenge.data.repository.MeliRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideMeliRepository(
        service: MeliService
    ): MeliRepository = MeliRepositoryImpl(service)
}