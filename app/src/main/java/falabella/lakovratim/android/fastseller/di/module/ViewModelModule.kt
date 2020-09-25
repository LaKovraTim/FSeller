package falabella.lakovratim.android.fastseller.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import falabella.lakovratim.android.fastseller.presentation.ui.order.OrderListViewModel
import falabella.lakovratim.android.fastseller.presentation.util.ViewModelFactory
import falabella.lakovratim.android.fastseller.presentation.util.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(OrderListViewModel::class)
    abstract fun bindOrderListViewModel(viewModel: OrderListViewModel): ViewModel
}