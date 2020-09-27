package falabella.lakovratim.android.fastseller.domain.repository

/**
 * @author   Andres Lobosz
 * @date    26-09-20.
 */

interface ILoginRepository {
    fun login(username: String, password: String): Boolean
    fun logout()
    fun getUser(): String
    fun getSession(): Pair<String, Boolean>
    fun setIP(ip: String)
    fun getIP(): String
}