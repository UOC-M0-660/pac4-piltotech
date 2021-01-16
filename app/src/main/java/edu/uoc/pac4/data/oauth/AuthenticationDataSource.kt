package edu.uoc.pac4.data.oauth

import android.util.Log
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Endpoints
import io.ktor.client.*
import io.ktor.client.request.*

class AuthenticationDataSource(private val sessionManager: SessionManager, private val httpClient: HttpClient): AuthenticationData {

    private val tag = "AuthenticationDataSource"

    override fun isUserAvailable(): Boolean {
        return sessionManager.isUserAvailable()
    }

    override fun saveAccessToken(accessToken: String) {
        sessionManager.saveAccessToken(accessToken)
    }

    override fun saveRefreshToken(refreshToken: String) {
        sessionManager.saveRefreshToken(refreshToken)
    }

    override fun clearAccessToken() {
        sessionManager.clearAccessToken()
    }

    override fun clearRefreshToken() {
        sessionManager.clearRefreshToken()
    }

    /// Gets Access and Refresh Tokens on Twitch
    override suspend fun getTokens(authorizationCode: String): OAuthTokensResponse? {
        // Get Tokens from Twitch
        return try {

            httpClient
                    .post<OAuthTokensResponse>(Endpoints.tokenUrl) {
                        parameter("client_id", OAuthConstants.clientID)
                        parameter("client_secret", OAuthConstants.clientSecret)
                        parameter("code", authorizationCode)
                        parameter("grant_type", "authorization_code")
                        parameter("redirect_uri", OAuthConstants.redirectUri)
                    }

        } catch (t: Throwable) {
            Log.w(tag, "Error Getting Access token", t)
            null
        }
    }

}