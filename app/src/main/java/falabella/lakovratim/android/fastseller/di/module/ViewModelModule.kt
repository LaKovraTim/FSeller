package falabella.lakovratim.android.fastseller.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import falabella.lakovratim.android.fastseller.presentation.util.ViewModelFactory

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}