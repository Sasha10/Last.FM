package com.notyteam.last_fm.dagger.modules

import androidx.lifecycle.ViewModel
import com.notyteam.last_fm.ui.viewmodels.GeoArtistViewModel
import com.notyteam.last_fm.dagger.ViewModelKey
import com.notyteam.last_fm.dagger.scopes.FragmentScope
import com.notyteam.last_fm.ui.viewmodels.AlbumsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModule {
    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(GeoArtistViewModel::class)
    abstract fun bindGeoArtistViewModel(model: GeoArtistViewModel): ViewModel

    @Binds
    @FragmentScope
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindAlbumsViewModel(model: AlbumsViewModel): ViewModel
}