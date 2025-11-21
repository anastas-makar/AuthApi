package pro.progr.authapi

import kotlinx.coroutines.flow.Flow

interface AuthInterface {
    fun getName() : String?

    fun setName(name : String?)

    fun getSessionId() : String?

    /**
     * Id сессии и
     * секрет, который сохраняется в зашифрованном виде один раз при авторизации
     */
    fun setSession(sessionId: String,
                   sessionSecret: String)

    fun clearSession()

    fun getEpochSecond() : Long

    fun getNonce() : String

    fun getHash(sessionId: String,
                deviceId: String,
                nonce: String,
                method: String,
                pathQuery: String,
                epochSecond: Long,
                bodyBytes: ByteArray) : String

    fun getSignAlg() : String

    fun getDeviceId() : String

    fun isAuthorized() : Flow<Boolean>
}