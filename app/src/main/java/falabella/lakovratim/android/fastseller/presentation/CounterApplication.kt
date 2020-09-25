package falabella.lakovratim.android.fastseller.presentation

import android.app.Application
import falabella.lakovratim.android.fastseller.di.component.AppComponent
import falabella.lakovratim.android.fastseller.di.component.DaggerAppComponent
import falabella.lakovratim.android.fastseller.presentation.CounterApplication.Companion.applicationComponent

class CounterApplication : Application() {

    companion object {
        lateinit var applicationComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        applicationComponent =
            DaggerAppComponent
                .builder()
                .context(this)
                .build()
        applicationComponent.inject(this)
    }
}

fun appComponent() = applicationComponent