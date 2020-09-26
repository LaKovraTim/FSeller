package falabella.lakovratim.android.fastseller.presentation.ui.taskdetail

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentVisitRegistrationBinding
import falabella.lakovratim.android.fastseller.presentation.util.BaseFragment

class VisitRegistrationFragment : BaseFragment<FragmentVisitRegistrationBinding>() {

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentVisitRegistrationBinding =
        FragmentVisitRegistrationBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rdoGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rdo_button_yes -> {
                    binding.visitRegisterRutInputLayout.defaultHintTextColor =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.greenBackground
                            )
                        )
                    binding.visitRegisterWhoReceiveInputLayout.defaultHintTextColor =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.greenBackground
                            )
                        )
                    binding.visitRegisterRutEditText.isEnabled = true
                    binding.visitRegisterWhoReceiveEditText.isEnabled = true
                }
                else -> {
                    binding.visitRegisterRutInputLayout.defaultHintTextColor =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grey
                            )
                        )
                    binding.visitRegisterWhoReceiveInputLayout.defaultHintTextColor =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.grey
                            )
                        )
                    binding.visitRegisterRutEditText.isEnabled = false
                    binding.visitRegisterWhoReceiveEditText.isEnabled = false
                }
            }
        }

        binding.btnTakePhoto.setOnClickListener { takePhoto() }

        binding.btnAccept.setOnClickListener {
            if (binding.rdoGroup.checkedRadioButtonId == -1) {
                showErrorDialog("Indique si entrego o no el pedido")
                return@setOnClickListener
            }
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