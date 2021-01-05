package edu.uoc.pac4.data.streams

import android.util.Log
import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Endpoints
import edu.uoc.pac4.data.network.UnauthorizedException
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*

class StreamsDataSource(private val sessionManager: SessionManager, private val httpClient: HttpClient): StreamsData {

    private val tag = "StreamsDataSource"

    override fun clearAccessToken() {
        sessionManager.clearAccessToken()
    }

    /// Gets Streams on Twitch
    @Throws(UnauthorizedException::class)
    override suspend fun getStreams(cursor: String?): StreamsResponse? {
        try {
            return httpClient
                    .get<StreamsResponse>(Endpoints.streamsUrl) {
                        cursor?.let { parameter("after", it) }
                    }
        } catch (t: Throwable) {
            Log.w(tag, "Error getting streams", t)
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