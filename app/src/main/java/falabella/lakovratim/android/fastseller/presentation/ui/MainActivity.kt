@file:Suppress("DEPRECATION")

package falabella.lakovratim.android.fastseller.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.work.WorkManager
import falabella.lakovratim.android.fastseller.data.remote.workRequest
import falabella.lakovratim.android.fastseller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        binding.fullScreenContainer.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        setContentView(binding.root)

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}