package falabella.lakovratim.android.fastseller.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import falabella.lakovratim.android.fastseller.R
import java.io.IOException
import java.security.GeneralSecurityException


class PreferencesDataSource(applicationContext: Context) :
    IPreferences {

    private var sharedPrefsEditor: SharedPreferences.Editor? = null
    private var sharedPrefs: SharedPreferences? = null

    init {
        try {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                sharedPrefs = applicationContext.getSharedPreferences(
                    applicationContext.getString(R.string.prefs_file),
                    Context.MODE_PRIVATE
                )
                sharedPrefsEditor = sharedPrefs!!.edit()
            } else {
                val keyGenParameterSpec = MasterKeys.AES256_GCM_SPEC
                val masterKeyAlias = MasterKeys.getOrCreate(keyGenParameterSpec)
                sharedPrefs = EncryptedSharedPreferences
                    .create(
                        "prefs",
                        masterKeyAlias,
                        applicationContext,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                    )
                sharedPrefsEditor = sharedPrefs!!.edit()
            }
        } catch (e: GeneralSecurityException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        // to try with stored user
        createUser(username = "alobosz", password = "123456")
        //createUser(username = "iroman", password = "123456")
        //createUser(username = "mauri", password = "123456")
        //createUser(username = "maxmartinez", password = "123456")
    }

    private fun createUser(username: String, password: String) {
        sharedPrefsEditor?.putString("username", username)?.apply()
        sharedPrefsEditor?.putString("password", password)?.apply()
    }

    override fun saveUser(username: String, password: String): Boolean {
            return sharedPrefs?.getString("username", "") == username &&
                    sharedPrefs?.getString("password", "") == password
    }

    override fun getUser(): Pair<String, String> {
        val username = sharedPrefs?.getString("username", "")
        val password = sharedPrefs?.getString("password", "")
        return Pair(username!!, password!!)
    }

    override fun clearUser() {
        sharedPrefsEditor?.putString("username", "")?.apply()
        sharedPrefsEditor?.putString("password", "")?.apply()
    }

    override fun putSession() {
        sharedPrefsEditor?.putBoolean("session", true)?.apply()
    }

    override fun deleteSession() {
        sharedPrefsEditor?.putBoolean("session", false)?.apply()
    }

    override fun getSession(): Boolean =
        sharedPrefs?.getBoolean("session", false) ?: false


}
