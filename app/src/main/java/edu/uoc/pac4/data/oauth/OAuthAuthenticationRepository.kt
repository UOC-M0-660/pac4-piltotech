package edu.uoc.pac4.data.oauth

import android.util.Log
import edu.uoc.pac4.data.datasources.LocalData

import edu.uoc.pac4.data.datasources.RemoteData

/**
 * Created by alex on 11/21/20.
 */
class OAuthAuthenticationRepository(
        private val localData: LocalData,
        private val remoteData: RemoteData
) : AuthenticationRepository {

    private val tag = "OAuthAuthenticationRepository"

    override suspend fun isUserAvailable(): Boolean {
        return localData.isUserAvailable()
    }

    override suspend fun login(authorizationCode: String): Boolean {
        // Launch get Tokens Request
        remoteData.getTokens(authorizationCode)?.let { response ->
            // Success :)

            Log.d(tag, "Got Access token ${response.accessToken}")

            // Save access token and refresh token using the SessionManager class
            localData.saveAccessToken(response.accessToken)
            response.refreshToken?.let {
                localData.saveRefreshToken(it)
            }
            return true
        }
        return false

    }

    override suspend fun logout() {
        localData.clearAccessToken()
        localData.clearRefreshToken()
    }
}