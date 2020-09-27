package falabella.lakovratim.android.fastseller.data.preferences

interface IPreferences {
    fun saveUser(username: String, password: String): Boolean
    fun getUser(): Pair<String, String>
    fun clearUser()
    fun putSession()
    fun deleteSession()
    fun getSession(): Boolean
    fun getIP() : String
    fun setIP(ip: String)
}