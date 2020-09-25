package falabella.lakovratim.android.fastseller.presentation.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import falabella.lakovratim.android.fastseller.R
import falabella.lakovratim.android.fastseller.presentation.util.extension.gone
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        lifecycleScope.launch() {
            whenStarted {
                delay(2000)
                splash.gone()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.loginContainer, LoginFragment(), null).commit()
            }
        }

    }
}