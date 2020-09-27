package falabella.lakovratim.android.fastseller.domain.interactor

import falabella.lakovratim.android.fastseller.domain.model.Order
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import falabella.lakovratim.android.fastseller.domain.util.BaseUseCase
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */
class SendOrderUseCase @Inject constructor(
    private val repository: IRepository
) : BaseUseCase<Order, Boolean>() {
    override suspend fun doOnBackground(params: Order): Boolean =
        repository.sendOrder(
            params.sellerId,
            params.workerId,
            params.image,
            params.comment,
            params.latitude,
            params.longitude,
            params.success
        )
}