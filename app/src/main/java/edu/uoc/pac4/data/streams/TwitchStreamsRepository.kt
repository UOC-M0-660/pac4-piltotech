package edu.uoc.pac4.data.streams


/**
 * Created by alex on 11/21/20.
 */

class TwitchStreamsRepository(
        private val dataSource: StreamsData
) : StreamsRepository {

    override suspend fun getStreams(cursor: String?): Pair<String?, List<Stream>> {
        var result:Pair<String?, List<Stream>>
        result = Pair("",emptyList())
        dataSource.getStreams(cursor)?.let { response ->
            result = Pair(response.pagination?.cursor,response.data.orEmpty())
        }
        return result
    }

    override fun clearDataOnError()
    {
        dataSource.clearAccessToken()
    }

}