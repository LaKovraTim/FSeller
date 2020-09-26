package falabella.lakovratim.android.fastseller.data

import falabella.lakovratim.android.fastseller.data.local.CounterDatabase
import falabella.lakovratim.android.fastseller.data.remote.CounterAPI
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    25-09-20.
 */
class Repository @Inject constructor(private val database: CounterDatabase, private val counterAPI: CounterAPI){
}