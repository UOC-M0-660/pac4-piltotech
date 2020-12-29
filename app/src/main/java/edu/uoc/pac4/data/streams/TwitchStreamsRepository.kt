package edu.uoc.pac4.data.streams


import edu.uoc.pac4.data.datasources.LocalData
import edu.uoc.pac4.data.datasources.RemoteData


/**
 * Created by alex on 11/21/20.
 */

class TwitchStreamsRepository(
        private val localData: LocalData,
        private val remoteData: RemoteData
) : StreamsRepository {

    override suspend fun getStreams(cursor: String?): Pair<String?, List<Stream>> {
        var result:Pair<String?, List<Stream>>
        result = Pair("",emptyList())
        remoteData.getStreams(cursor)?.let { response ->
            result = Pair(response.pagination?.cursor,response.data.orEmpty())
        }
        return result
    }

    override fun clearDataOnError()
    {
        localData.clearAccessToken()
    }

}