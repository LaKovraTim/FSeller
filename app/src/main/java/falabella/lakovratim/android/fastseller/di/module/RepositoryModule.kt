package falabella.lakovratim.android.fastseller.di.module

import dagger.Binds
import dagger.Module
import falabella.lakovratim.android.fastseller.data.Repository
import falabella.lakovratim.android.fastseller.domain.repository.IRepository

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindLoginPublicRepository(repository: Repository): IRepository

}