package edu.uoc.pac4.data.streams

interface StreamsData {

    fun clearAccessToken()

    suspend fun getStreams(cursor: String? = null): StreamsResponse?
}