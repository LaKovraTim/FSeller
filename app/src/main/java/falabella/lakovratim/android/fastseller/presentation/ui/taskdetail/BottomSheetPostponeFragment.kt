package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.NonNull
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.BottomsheetPostponeBinding
import java.text.SimpleDateFormat
import java.util.*

class BottomSheetPostponeFragment : BottomSheetDialogFragment() {

    private var _binding: BottomsheetPostponeBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialogThemeNoFloating)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BottomsheetPostponeBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dateEditText.text =
            SimpleDateFormat(getString(R.string.date_format)).format(System.currentTimeMillis())

        binding.voucherButtonDone.setOnClickListener {
            dialog?.dismiss()
        }
        val c = Calendar.getInstance()
        val myFormat = getString(R.string.date_format)
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.dateEditText.text = dateFormat.format(c.time)
        binding.dateEditText.setOnClickListener {
            DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    c.set(Calendar.YEAR, year)
                    c.set(Calendar.MONTH, monthOfYear)
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    (it as? TextView)?.text = dateFormat.format(c.time)
                },
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener {
            val bottomSheetDialog = dialog as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(R.id.design_bottom_sheet)
            if (null != bottomSheet) {
                BottomSheetBehavior.from<View>(bottomSheet).state =
                    BottomSheetBehavior.STATE_EXPANDED
                BottomSheetBehavior.from<View>(bottomSheet)
                    .addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                        override fun onStateChanged(@NonNull bottomSheet: View, newState: Int) {
                            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                            }
                        }

                        override fun onSlide(@NonNull bottomSheet: View, slideOffset: Float) {}
                    })
            }
        }
        dialog.setCanceledOnTouchOutside(true)
        return dialog
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}