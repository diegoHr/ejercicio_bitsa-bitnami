package com.diegohr.ejercicio_bitsa_bitnovo.di

import com.diegohr.ejercicio_bitsa_bitnovo.model.CastleStatusRepository
import com.diegohr.ejercicio_bitsa_bitnovo.model.GamePlayService
import com.diegohr.ejercicio_bitsa_bitnovo.model.WindowUtils
import com.diegohr.ejercicio_bitsa_bitnovo.model.WinnersFinderService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

/**
 * Created by Diego Hernando on 9/6/21.
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class GeneralModule {

    @Binds
    abstract fun providesCastleStatusRepository(impl: CastleStatusRepository): CastleStatusRepository

    @Binds
    abstract fun providesGamePlayService(impl: GamePlayService): GamePlayService

    @Binds
    abstract fun providesWinnersFinderService(impl: WinnersFinderService): WinnersFinderService

    @Binds
    abstract fun providesWindowUtils(impl: WindowUtils): WindowUtils
}