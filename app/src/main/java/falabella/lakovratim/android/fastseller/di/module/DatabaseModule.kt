package falabella.lakovratim.android.fastseller.di.module

import android.content.Context
import androidx.room.Room
import falabella.lakovratim.android.fastseller.data.local.CounterDatabase
import dagger.Module
import dagger.Provides
import falabella.lakovratim.android.fastseller.data.local.DATABASE_NAME
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(context: Context): CounterDatabase =
        Room.databaseBuilder(
            context,
            CounterDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries().build()
}