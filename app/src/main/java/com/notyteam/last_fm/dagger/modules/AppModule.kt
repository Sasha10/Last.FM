package com.notyteam.last_fm.dagger.modules

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.notyteam.last_fm.api.retfofit.apis.BaseApi
import com.notyteam.last_fm.api.retfofit.apis.GeoArtistApi
import com.notyteam.last_fm.api.retfofit.api_interface.GeoArtistApiInterface
import com.notyteam.last_fm.api.retfofit.api_interface.TopAlbumsApiInterface
import com.notyteam.last_fm.api.retfofit.apis.TopAlbumApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {
    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideToast(context: Context): Toast = Toast.makeText(context, "", Toast.LENGTH_LONG)

    @Provides
    fun provideRequestsDisposable() = CompositeDisposable()

    @Provides
    @Singleton
    fun provideBaseApi(context: Context): BaseApi = BaseApi(context)


    @Provides
    @Singleton
    fun provideGeoArtistApi(context: Context, retrofit: Retrofit, api: GeoArtistApiInterface):
            GeoArtistApi = GeoArtistApi(context, retrofit, api)

    @Provides
    @Singleton
    fun provideTopAlbumsApi(context: Context, retrofit: Retrofit, api: TopAlbumsApiInterface):
            TopAlbumApi = TopAlbumApi(context, retrofit, api)


    @Provides
    @Singleton
    fun provideCicerone(): Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun provideCiceroneRouter(cicerone: Cicerone<Router>): Router = cicerone.router

    @Provides
    @Singleton
    fun provideCiceroneNavigator(cicerone: Cicerone<Router>): NavigatorHolder = cicerone.navigatorHolder

}