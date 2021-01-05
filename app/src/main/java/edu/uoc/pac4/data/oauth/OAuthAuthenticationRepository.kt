package edu.uoc.pac4.data.oauth

import android.util.Log

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(
        private val dataSource: AuthenticationData
) : AuthenticationRepository {

    private val tag = "OAuthAuthenticationRepository"

    override suspend fun isUserAvailable(): Boolean {
        return dataSource.isUserAvailable()
    }

    override suspend fun login(authorizationCode: String): Boolean {
        // Launch get Tokens Request
        dataSource.getTokens(authorizationCode)?.let { response ->
            // Success :)

            Log.d(tag, "Got Access token ${response.accessToken}")

            // Save access token and refresh token using the SessionManager class
            dataSource.saveAccessToken(response.accessToken)
            response.refreshToken?.let {
                dataSource.saveRefreshToken(it)
            }
            return true
        }
        return false

    }

    override suspend fun logout() {
        dataSource.clearAccessToken()
        dataSource.clearRefreshToken()
    }
}