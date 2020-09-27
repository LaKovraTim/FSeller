package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentTaskDetailBinding
import falabella.lakovratim.android.fastseller.domain.model.OrderOptions
import falabella.lakovratim.android.fastseller.presentation.appComponent
import falabella.lakovratim.android.fastseller.presentation.ui.MainActivityViewModel
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment
import falabella.lakovratim.android.fastseller.presentation.util.OrderMenu
import falabella.lakovratim.android.fastseller.presentation.util.extension.visible
import javax.inject.Inject


class TaskDetailFragment : BaseFragment<FragmentTaskDetailBinding>() {

    @Inject
    lateinit var orderOptionsAdapter: OrderOptionsAdapter

    private val viewModel: MainActivityViewModel by activityViewModels { viewModelFactory }

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTaskDetailBinding = FragmentTaskDetailBinding.inflate(inflater, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showFilters()

        binding.include.headerBack.setOnClickListener {
            activity?.onBackPressed()
        }

        showWorker()
    }

    private fun showWorker() {
        viewModel.workOrder.value?.let {
            binding.textDetailNumber.text =  getString(R.string.text_order_detail_with_number, it.id)
            binding.textRealizedByValue.text =
                "${it.customer?.firstName} ${it.customer?.secondName}"
            binding.textCreationDate.text = it.creationDate!!
            binding.textAmount.text = "$${(it.total ?: "").toString()}"
            binding.textReceiverName.text =
                "${it.customer?.receiver?.firstName} ${it.customer?.receiver?.secondName}"
            binding.textAddress.text =
                "${it.customer?.address?.street} ${it.customer?.address?.number} ${it.customer?.address?.comuna}"
            binding.textReceiverDate.text = it.deliveryDate


            if (it.cashOnDelivery) {
                binding.cashOnDelivery.visible()
            }
        }
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private fun showFilters() {
        binding.recyclerViewOptions.adapter = orderOptionsAdapter.apply {
            items = listOf(
                OrderOptions(
                    OrderMenu.SeeMap,
                    resources.getDrawable(R.drawable.ic_place, null),
                    getString(R.string.text_see_map)
                ),
                OrderOptions(
                    OrderMenu.Delivered,
                    resources.getDrawable(R.drawable.ic_check, null),
                    "Registrar\nvisita"
                ),

                OrderOptions(
                    OrderMenu.PayWithQR,
                    resources.getDrawable(R.drawable.ic_qai_pago_directo_qr, null),
                    "Pago\ncon QR"
                ),

                OrderOptions(
                    OrderMenu.Call,
                    resources.getDrawable(R.drawable.ic_phone_in_talk, null),
                    "Llamar\nal cliente"
                ),


                OrderOptions(
                    OrderMenu.Refuse, resources.getDrawable(R.drawable.ic_tv_off_rounded, null),
                    getString(R.string.text_refuse)
                ),
            )
            option = ::orderOptions

        }

        /*     val lim = GridLayoutManager(context, 5)
             lim.orientation = LinearLayoutManager.VERTICAL
             binding.recyclerViewOptions.layoutManager = lim*/
    }


    private fun orderOptions(option: OrderMenu) {
        when (option) {
            is OrderMenu.SeeMap -> openWaze()
            is OrderMenu.Delivered -> {
                findNavController().navigate(R.id.action_taskDetailFragment_to_visitRegistrationFragment)
            }
            is OrderMenu.Postpone -> {
                BottomSheetPostponeFragment().show(childFragmentManager.beginTransaction(), null)
                //findNavController().navigate(R.id.action_taskDetailFragment_to_bottomSheetPostponeFragment)
            }
            is OrderMenu.Refuse -> {

            }

            is OrderMenu.PayWithQR -> {
                findNavController().navigate(R.id.paymentFragment)
            }

            is OrderMenu.Call -> callCustomer()
        }

    }

    fun openWaze() {
        try {
            val url =
                "waze://?ll=" + viewModel.workOrder.value?.customer?.address?.location?.lat + ", " + viewModel.workOrder.value?.customer?.address?.location?.lon + "&navigate=yes"

            val intentWaze = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            intentWaze.setPackage("com.waze")
            val uriGoogle =
                "google.navigation:q=" + viewModel.workOrder.value?.customer?.address?.location?.lat + "," + viewModel.workOrder.value?.customer?.address?.location?.lon

            val intentGoogleNav = Intent(Intent.ACTION_VIEW, Uri.parse(uriGoogle))
            intentGoogleNav.setPackage("com.google.android.apps.maps")
            val title: String = "Vamos!!"
            val chooserIntent = Intent.createChooser(intentGoogleNav, title)
            val arr = arrayOfNulls<Intent>(1)
            arr[0] = intentWaze
            chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arr)
            context?.startActivity(chooserIntent)
        } catch (e: Exception) {
            Toast.makeText(context, "No es posible obtener la localización", Toast.LENGTH_SHORT)
                .show()
        }
    }


    private fun callCustomer() {
        try {
            val surf = Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:" + viewModel.workOrder.value?.customer?.contact?.phone)
            )
            requireActivity().startActivity(surf)
        } catch (e: Exception) {
            Toast.makeText(
                context,
                "No es posible obtener el número del cliente",
                Toast.LENGTH_SHORT
            )
                .show()
        }

    }
}

