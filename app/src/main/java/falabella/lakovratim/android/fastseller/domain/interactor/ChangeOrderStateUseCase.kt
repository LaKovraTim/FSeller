package falabella.lakovratim.android.fastseller.domain.interactor

import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import falabella.lakovratim.android.fastseller.domain.util.BaseUseCase
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    27-09-20.
 */
class ChangeOrderStateUseCase @Inject constructor(
    private val repository: IRepository
) : BaseUseCase<Pair<String, List<String>>, Boolean>() {
    override suspend fun doOnBackground(params: Pair<String, List<String>>): Boolean =
        repository.changeOrderState(params)
}