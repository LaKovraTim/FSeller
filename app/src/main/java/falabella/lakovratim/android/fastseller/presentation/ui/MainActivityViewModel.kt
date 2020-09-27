package falabella.lakovratim.android.fastseller.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import falabella.lakovratim.android.fastseller.domain.interactor.ChangeOrderStateUseCase
import falabella.lakovratim.android.fastseller.domain.interactor.GetWorkOrdersUseCase
import falabella.lakovratim.android.fastseller.domain.interactor.SendOrderUseCase
import falabella.lakovratim.android.fastseller.domain.model.Customer
import falabella.lakovratim.android.fastseller.domain.model.Order
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import falabella.lakovratim.android.fastseller.domain.util.Failure
import falabella.lakovratim.android.fastseller.presentation.util.Resource
import falabella.lakovratim.android.fastseller.presentation.util.SingleLiveEvent
import java.io.File
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */

open class MainActivityViewModel @Inject constructor(
    private val repository: IRepository,
    private val getWorkOrdersUseCase: GetWorkOrdersUseCase,
    private val sendOrderUseCase: SendOrderUseCase,
    private val changeOrderStateUseCase: ChangeOrderStateUseCase
) : ViewModel() {
    private val _workOrders = MutableLiveData<Resource<List<WorkOrder>>>()
    val workOrders: LiveData<Resource<List<WorkOrder>>>
        get() = _workOrders

    private val _sendOrder = SingleLiveEvent<Resource<Boolean>>()
    val sendOrder: SingleLiveEvent<Resource<Boolean>>
        get() = _sendOrder

    private val _changeOrderState = SingleLiveEvent<Resource<Boolean>>()
    val changeOrderState: SingleLiveEvent<Resource<Boolean>>
        get() = _changeOrderState

    val workOrder = MutableLiveData<WorkOrder?>()
    fun setWorkOrderSelected(orderResponse: WorkOrder?) {
        this.workOrder.value = orderResponse
    }

    fun getOrders() {
        _workOrders.postValue(Resource.Loading())
        fun handleFailure(failure: Failure) {
            _workOrders.postValue(Resource.Error(failure))
        }

        fun handleSuccess(response: List<WorkOrder>) {
            _workOrders.postValue(Resource.Success(response))
        }
        getWorkOrdersUseCase.execute("100", ::handleSuccess, ::handleFailure)
    }

    fun sendOrder(photo: File? = null, comment: String?, success: Boolean) {
        _sendOrder.postValue(Resource.Loading())
        fun handleFailure(failure: Failure) {
            _sendOrder.postValue(Resource.Error(failure))
        }

        fun handleSuccess(response: Boolean) {
            _sendOrder.postValue(Resource.Success(response))
        }
        sendOrderUseCase.execute(
            Order(
                "100",
                workOrder.value?.id,
                null,
                comment,
                workOrder.value?.customer?.address?.location?.lat?.toDouble(),
                workOrder.value?.customer?.address?.location?.lon?.toDouble(),
                success
            ), ::handleSuccess, ::handleFailure
        )
    }

    fun changeOrdersState(workerIds: List<String>) {
        _changeOrderState.postValue(Resource.Loading())
        fun handleFailure(failure: Failure) {
            _changeOrderState.postValue(Resource.Error(failure))
        }

        fun handleSuccess(response: Boolean) {
            _changeOrderState.postValue(Resource.Success(response))
        }
        changeOrderStateUseCase.execute(Pair("100",workerIds), ::handleSuccess, ::handleFailure)
    }
}