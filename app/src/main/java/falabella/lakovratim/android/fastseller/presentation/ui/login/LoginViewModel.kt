package falabella.lakovratim.android.fastseller.presentation.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import falabella.lakovratim.android.fastseller.data.LoginRepository
import falabella.lakovratim.android.fastseller.data.preferences.PreferencesDataSource

class LoginViewModel(app: Application) : AndroidViewModel(app) {
    private val sharedPrefs: PreferencesDataSource by lazy {
        PreferencesDataSource(app)
    }
    private val loginRepository: LoginRepository = LoginRepository(sharedPrefs)


    fun hasSession() =
        loginRepository.getSession()


    fun login(user: String, password: String) =
        loginRepository.login(user, password)

    fun logout() {
        loginRepository.logout()
    }

}