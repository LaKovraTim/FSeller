package falabella.lakovratim.android.fastseller.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import falabella.lakovratim.android.fastseller.di.module.DatabaseModule
import falabella.lakovratim.android.fastseller.di.module.RepositoryModule
import falabella.lakovratim.android.fastseller.di.module.RetrofitModule
import falabella.lakovratim.android.fastseller.di.module.ViewModelModule
import falabella.lakovratim.android.fastseller.presentation.CounterApplication
import falabella.lakovratim.android.fastseller.presentation.ui.order.OrderListFragment
import falabella.lakovratim.android.fastseller.presentation.ui.taskdetail.TaskDetailFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class,
        RetrofitModule::class,
        RepositoryModule::class,
        DatabaseModule::class]
)
interface AppComponent : FragmentInjector {
    fun inject(counterApplication: CounterApplication)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun context(context: Context): Builder
    }
}

interface FragmentInjector {
    fun inject(orderListFragment: OrderListFragment)
    fun inject(taskDetailFragment: TaskDetailFragment)


}