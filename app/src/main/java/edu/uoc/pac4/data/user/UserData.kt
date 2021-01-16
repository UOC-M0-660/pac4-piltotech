package edu.uoc.pac4.data.user

interface UserData {

    fun clearAccessToken()

    fun clearRefreshToken()

    suspend fun getUser(): User?

    suspend fun updateUserDescription(description: String): User?
}