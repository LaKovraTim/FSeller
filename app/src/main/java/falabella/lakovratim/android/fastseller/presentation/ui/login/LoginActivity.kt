@file:Suppress("DEPRECATION")

package falabella.lakovratim.android.fastseller.presentation.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.presentation.ui.MainActivity
import falabella.lakovratim.android.fastseller.presentation.util.extension.finishWithFade
import falabella.lakovratim.android.fastseller.presentation.util.extension.gone
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel: LoginViewModel by viewModels()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        fullScreenContainer.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        lifecycleScope.launch() {
            whenStarted {
                delay(3000)
                val session = viewModel.hasSession()
                if (session.second) {
                    Intent(baseContext, MainActivity::class.java).also {
                        startActivity(it)
                    }
                    finishWithFade()
                } else {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.loginContainer, LoginFragment(), null).commit()

                }
                splash.gone()
            }
        }

    }
}