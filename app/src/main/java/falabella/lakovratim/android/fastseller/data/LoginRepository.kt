package falabella.lakovratim.android.fastseller.data

import falabella.lakovratim.android.fastseller.data.preferences.PreferencesDataSource
import falabella.lakovratim.android.fastseller.domain.repository.ILoginRepository

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */
class LoginRepository(private val sharedPreferences: PreferencesDataSource) : ILoginRepository {


    override fun login(username: String, password: String): Boolean {
        with(receiver = sharedPreferences.saveUser(username, password)) {
            if (this)
                sharedPreferences.putSession()
            return this
        }
    }

    override fun logout() {
        sharedPreferences.deleteSession()
    }

    override fun getUser(): String {
        return sharedPreferences.getUser().first
    }

    override fun getSession(): Pair<String, Boolean> {
        return Pair(sharedPreferences.getUser().first, sharedPreferences.getSession())

    }

    override fun setIP(ip: String) {
        sharedPreferences.setIP(ip)
    }

    override fun getIP(): String =
        sharedPreferences.getIP()
}