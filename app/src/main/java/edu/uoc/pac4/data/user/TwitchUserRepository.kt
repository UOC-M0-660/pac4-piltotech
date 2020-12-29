package edu.uoc.pac4.data.user


import edu.uoc.pac4.data.datasources.LocalData
import edu.uoc.pac4.data.datasources.LocalDataSource
import edu.uoc.pac4.data.datasources.RemoteData


/**
 * Created by alex on 11/21/20.
 */

class TwitchUserRepository(
        private val localData: LocalData,
        private val remoteData: RemoteData
) : UserRepository {

    override suspend fun getUser(): User? {
        return remoteData.getUser()
    }

    override suspend fun updateUser(description: String): User? {
        return remoteData.updateUserDescription(description)
    }

    override fun clearDataoOnUnauthorized()
    {
        localData.clearAccessToken()
    }

    override fun clearDataoOnLogout()
    {
        localData.clearAccessToken()
        localData.clearRefreshToken()
    }
}