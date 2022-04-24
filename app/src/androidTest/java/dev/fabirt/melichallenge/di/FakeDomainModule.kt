package dev.fabirt.melichallenge.di

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import dev.fabirt.melichallenge.data.repository.FakeMeliRepositoryImpl
import dev.fabirt.melichallenge.domain.repository.MeliRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DomainModule::class]
)
object FakeDomainModule {

    @Provides
    @Singleton
    fun provideMeliRepository(): MeliRepository = FakeMeliRepositoryImpl()
}