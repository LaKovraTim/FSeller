@file:Suppress("DEPRECATION")

package falabella.lakovratim.android.fastseller.presentation.ui.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.databinding.ActivityLoginBinding
import falabella.lakovratim.android.fastseller.presentation.util.extension.gone
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.fullScreenContainer.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        lifecycleScope.launch() {
            whenStarted {
                delay(2000)
                supportFragmentManager.beginTransaction()
                    .replace(R.id.loginContainer, LoginFragment(), null).commit()
                splash.gone()
            }
        }

    }
}