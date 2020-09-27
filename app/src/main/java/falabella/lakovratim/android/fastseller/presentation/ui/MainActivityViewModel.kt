package falabella.lakovratim.android.fastseller.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import falabella.lakovratim.android.fastseller.domain.interactor.GetWorkOrdersUseCase
import falabella.lakovratim.android.fastseller.domain.interactor.SendOrderUseCase
import falabella.lakovratim.android.fastseller.domain.model.Customer
import falabella.lakovratim.android.fastseller.domain.model.Order
import falabella.lakovratim.android.fastseller.domain.model.WorkOrder
import falabella.lakovratim.android.fastseller.domain.repository.IRepository
import falabella.lakovratim.android.fastseller.domain.util.Failure
import falabella.lakovratim.android.fastseller.presentation.util.Resource
import java.io.File
import javax.inject.Inject

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */

open class MainActivityViewModel @Inject constructor(
    private val repository: IRepository,
    private val getWorkOrdersUseCase: GetWorkOrdersUseCase,
    private val sendOrderUseCase: SendOrderUseCase
) : ViewModel() {
    private val _workOrders = MutableLiveData<Resource<List<WorkOrder>>>()
    val workOrders: LiveData<Resource<List<WorkOrder>>>
        get() = _workOrders

    private val _sendOrder = MutableLiveData<Resource<Boolean>>()
    val sendOrder: LiveData<Resource<Boolean>>
        get() = _sendOrder

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
//        getWorkOrdersUseCase.execute("100", ::handleSuccess, ::handleFailure)

        _workOrders.postValue(
            Resource.Success(
                listOf(
                    WorkOrder(
                        true,
                        "",
                        "100",
                        "3333",
                        Customer(null, null, "Igor", null, "Qui pa"),
                        "222",
                        "123456",
                        arrayListOf(),
                        1234, arrayListOf(), "", 2020
                    ),
                    WorkOrder(
                        true,
                        "",
                        "100",
                        "3333",
                        Customer(null, null, "Igor", null, "Qui pa"),
                        "222",
                        "123456444",
                        arrayListOf(),
                        1234444, arrayListOf(), "", 2020
                    )
                )
            )
        )
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
}