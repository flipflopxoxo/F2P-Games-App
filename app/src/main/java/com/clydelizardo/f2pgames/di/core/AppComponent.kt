package com.clydelizardo.f2pgames.di.core

import android.app.Application
import com.clydelizardo.f2pgames.di.core.viewmodel.ViewModelFactoryModule
import com.clydelizardo.f2pgames.di.detail.GameDetailsComponent
import com.clydelizardo.f2pgames.di.list.GameListComponent
import com.clydelizardo.f2pgames.di.repository.AppDatabaseModule
import com.clydelizardo.f2pgames.di.repository.GameInfoDaoModule
import com.clydelizardo.f2pgames.di.repository.GameInfoFavoritesRepositoryModule
import com.clydelizardo.f2pgames.di.repository.GameInfoServiceModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppDatabaseModule::class, ViewModelFactoryModule::class,
        GameInfoFavoritesRepositoryModule::class, GameInfoServiceModule::class,
        GameInfoDaoModule::class, AndroidInjectionModule::class]
)
interface AppComponent {
    fun gameListComponent(): GameListComponent.Factory
    fun gameDetailComponent(): GameDetailsComponent.Factory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}