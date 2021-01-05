package edu.uoc.pac4.data.user


/**
 * Created by alex on 11/21/20.
 */

class TwitchUserRepository(
        private val dataSource: UserData
) : UserRepository {

    override suspend fun getUser(): User? {
        return dataSource.getUser()
    }

    override suspend fun updateUser(description: String): User? {
        return dataSource.updateUserDescription(description)
    }

    override fun clearDataOnUnauthorized() {
        dataSource.clearAccessToken()
    }

    override fun clearDataOnLogout() {
        dataSource.clearAccessToken()
        dataSource.clearRefreshToken()
    }
}