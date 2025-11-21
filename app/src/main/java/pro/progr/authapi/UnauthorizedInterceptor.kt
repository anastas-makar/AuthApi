package pro.progr.authapi


import okhttp3.Interceptor
import okhttp3.Response

class UnauthorizedInterceptor(private val auth: AuthInterface) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val resp = chain.proceed(chain.request())
        if (resp.code == 401 || resp.code == 403) {
            // сессия битая/отозвана — локально выходим
            auth.clearSession()
        }
        return resp
    }
}