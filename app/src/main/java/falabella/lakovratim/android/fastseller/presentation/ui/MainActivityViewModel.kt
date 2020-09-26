package falabella.lakovratim.android.fastseller.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import falabella.lakovratim.android.fastseller.domain.interactor.GetWorkOrdersUseCase
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import falabella.lakovratim.android.fastseller.domain.util.Failure
import falabella.lakovratim.android.fastseller.presentation.util.Resource
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */

open class MainActivityViewModel @Inject constructor(
    private val repository: IRepository,
    private val getWorkOrdersUseCase: GetWorkOrdersUseCase
) : ViewModel() {
    private val _workOrders = MutableLiveData<Resource<List<WorkOrder>>>()
    val workOrders: LiveData<Resource<List<WorkOrder>>>
        get() = _workOrders

    fun getOrders(){
        _workOrders.postValue(Resource.Loading())
        fun handleFailure(failure: Failure) {
            _workOrders.postValue(Resource.Error(failure))
        }

        fun handleSuccess(response: List<WorkOrder>) {
            _workOrders.postValue(Resource.Success(response))
        }
        getWorkOrdersUseCase.execute("100", ::handleSuccess, ::handleFailure)

    }
}