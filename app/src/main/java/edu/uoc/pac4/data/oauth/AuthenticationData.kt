package edu.uoc.pac4.data.oauth

interface AuthenticationData {

    fun isUserAvailable(): Boolean

    fun saveAccessToken(accessToken: String)

    fun saveRefreshToken(refreshToken: String)

    fun clearAccessToken()

    fun clearRefreshToken()

    suspend fun getTokens(authorizationCode: String): OAuthTokensResponse?
}