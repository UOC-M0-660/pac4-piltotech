package edu.uoc.pac4.data.user

import android.util.Log
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.network.UnauthorizedException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class UserDataSource(private val sessionManager: SessionManager, private val httpClient: HttpClient) : UserData {

    private val tag = "UserDataSource"

    override fun clearAccessToken() {
        sessionManager.clearAccessToken()
    }

    override fun clearRefreshToken() {
        sessionManager.clearRefreshToken()
    }

    /// Gets Current Authorized User on Twitch
    @Throws(UnauthorizedException::class)
    override suspend fun getUser(): User? {
        try {
            val response = httpClient
                    .get<UsersResponse>(Endpoints.usersUrl)

            return response.data?.firstOrNull()
        } catch (t: Throwable) {
            Log.w(tag, "Error getting user", t)
            // Try to handle error
            return when (t) {
                is ClientRequestException -> {
                    // Check if it's a 401 Unauthorized
                    if (t.response?.status?.value == 401) {
                        throw UnauthorizedException
                    }
                    null
                }
                else -> null
            }
        }
    }

    /// Gets Current Authorized User on Twitch
    @Throws(UnauthorizedException::class)
    override suspend fun updateUserDescription(description: String): User? {
        try {
            val response = httpClient
                    .put<UsersResponse>(Endpoints.usersUrl) {
                        parameter("description", description)
                    }

            return response.data?.firstOrNull()
        } catch (t: Throwable) {
            Log.w(tag, "Error updating user description", t)
            // Try to handle error
            return when (t) {
                is ClientRequestException -> {
                    // Check if it's a 401 Unauthorized
                    if (t.response?.status?.value == 401) {
                        throw UnauthorizedException
                    }
                    null
                }
                else -> null
            }
        }
    }
}