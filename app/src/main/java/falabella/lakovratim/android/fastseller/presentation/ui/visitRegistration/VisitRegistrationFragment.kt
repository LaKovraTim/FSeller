package falabella.lakovratim.android.fastseller.presentation.ui.visitRegistration

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentVisitRegistrationBinding
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment
import falabella.lakovratim.android.fastseller.presentation.util.extension.gone
import falabella.lakovratim.android.fastseller.presentation.util.extension.visible
import java.text.SimpleDateFormat
import java.util.*

class VisitRegistrationFragment : BaseFragment<FragmentVisitRegistrationBinding>() {

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVisitRegistrationBinding =
        FragmentVisitRegistrationBinding.inflate(inflater, container, false)

    @SuppressLint("SimpleDateFormat")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.include.headerBack.setOnClickListener {
            activity?.onBackPressed()
        }

        val c = Calendar.getInstance()
        val myFormat = getString(R.string.date_format)
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.visitRegisterDateEditText.setText(dateFormat.format(c.time))

        val deliveryDate = getString(R.string.delivety_date_label)
        val reschedule = getString(R.string.reschedule)

        binding.rdoGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rdo_button_yes -> {
                    binding.visitRegisterRutInputLayout.visible()
                    binding.visitRegisterWhoReceiveInputLayout.visible()
                    binding.visitRegisterDateInputLayout.hint = deliveryDate
                    binding.visitRegisterCalendar.setOnClickListener {}
                }
                else -> {
                    binding.visitRegisterRutInputLayout.gone()
                    binding.visitRegisterWhoReceiveInputLayout.gone()
                    binding.visitRegisterDateInputLayout.hint = reschedule
                    binding.visitRegisterCalendar.setOnClickListener {
                        DatePickerDialog(
                            requireContext(),
                            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                                c.set(Calendar.YEAR, year)
                                c.set(Calendar.MONTH, monthOfYear)
                                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                                binding.visitRegisterDateEditText.setText(dateFormat.format(c.time))
                            },
                            c.get(Calendar.YEAR),
                            c.get(Calendar.MONTH),
                            c.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
                }
            }
        }

        binding.rdoGroup.check(R.id.rdo_button_yes)


        binding.btnTakePhoto.setOnClickListener { takePhoto() }

        binding.btnAccept.setOnClickListener {

            when (binding.rdoGroup.checkedRadioButtonId) {
                R.id.rdo_button_yes -> {
                    if (binding.visitRegisterWhoReceiveEditText.text?.isEmpty() == true) {
                        showErrorDialog("Ingrese el nombre de quien recibe el pedido")
                        binding.visitRegisterWhoReceiveEditText.requestFocus()
                        return@setOnClickListener
                    }
                    if (binding.visitRegisterRutEditText.text?.isEmpty() == true) {
                        showErrorDialog("Ingrese el Rut de quien recibe el pedido")
                        binding.visitRegisterRutEditText.requestFocus()
                        return@setOnClickListener
                    }
                }
                R.id.rdo_button_no -> {
                    if (binding.visitRegisterCommentEditText.text?.isEmpty() == true) {
                        showErrorDialog("Ingrese motivo por el cual \nNO pudo entregar el pedido")
                        binding.visitRegisterCommentEditText.requestFocus()
                        return@setOnClickListener
                    }
                }
                else -> {
                    showErrorDialog("Indique si entrego o no el pedido")
                    return@setOnClickListener
                }
            }

            //TODO call to service
        }
    }

    private fun showErrorDialog(message: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton(
                "Ok"
            ) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private fun takePhoto() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            binding.photo.setImageBitmap(imageBitmap)
        }
    }

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 200
    }
}