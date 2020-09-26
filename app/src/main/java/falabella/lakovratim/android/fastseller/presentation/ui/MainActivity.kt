@file:Suppress("DEPRECATION")

package falabella.lakovratim.android.fastseller.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import falabella.lakovratim.android.fastseller.databinding.ActivityMainBinding
import falabella.lakovratim.android.fastseller.presentation.ui.login.LoginViewModel
import falabella.lakovratim.android.fastseller.presentation.util.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fullScreenContainer.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(binding.root)

//        WorkManager.getInstance(this).enqueue(workRequest)
    }
}