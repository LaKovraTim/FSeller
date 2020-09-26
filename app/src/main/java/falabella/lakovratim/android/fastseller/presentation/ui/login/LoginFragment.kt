package falabella.lakovratim.android.fastseller.presentation.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.FragmentLoginBinding
import falabella.lakovratim.android.fastseller.presentation.ui.MainActivity
import falabella.lakovratim.android.fastseller.presentation.ui.taskdetail.BottomSheetDeliveryFragment
import falabella.lakovratim.android.fastseller.presentation.util.Constant.USERNAME_PATTERN
import falabella.lakovratim.android.fastseller.presentation.util.Constant.USER_ARG
import falabella.lakovratim.android.fastseller.presentation.util.extension.addSpaceFilter
import falabella.lakovratim.android.fastseller.presentation.util.extension.finishWithFade
import falabella.lakovratim.android.fastseller.presentation.util.extension.webLink
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.regex.Pattern


class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding
        get() = _binding!!

    private var isValidUsername = false
    private var isValidPass = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(receiver = editTextTextUser) {
            addSpaceFilter()
            doAfterTextChanged {
                it?.let {
                    isValidPass = it.length >= 6
                }
            }
        }

        with(receiver = editTextTextUser) {
            addSpaceFilter()
            doAfterTextChanged {
                it?.let {
                    isValidUsername = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches() ||
                            Pattern.compile(USERNAME_PATTERN).matcher(it).matches()
                }
            }
        }

        binding.forgotPassword.webLink(R.color.textColor)

        binding.buttonLogin.setOnClickListener {
            if (binding.editTextTextPassword.text.isNullOrEmpty() || binding.editTextTextUser.text.isNullOrEmpty()) {
                Toast.makeText(context, getString(R.string.empty_credencials), Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            if (!validateFields()) {
                return@setOnClickListener
            }
            if (viewModel.login(
                    binding.editTextTextUser.text.toString(),
                    binding.editTextTextPassword.text.toString()
                )
            )
                showOrders()
            else{
                Toast.makeText(context, getString(R.string.wrong_username), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun showOrders() {
        Intent(context, MainActivity::class.java).also {
                startActivity(it)
            }
        activity?.finishWithFade()
    }

    private fun validateFields(): Boolean =
        isValidPass && isValidUsername


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}