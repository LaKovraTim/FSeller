package falabella.lakovratim.android.fastseller.domain.interactor

import falabella.lakovratim.android.fastseller.data.Repository
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import falabella.lakovratim.android.fastseller.domain.util.BaseUseCase
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */
class GetWorkOrdersUseCase @Inject constructor(
    private val repository: IRepository
) : BaseUseCase<String, List<WorkOrder>>() {
    override suspend fun doOnBackground(params: String): List<WorkOrder> =
        repository.getOrders(params)


}