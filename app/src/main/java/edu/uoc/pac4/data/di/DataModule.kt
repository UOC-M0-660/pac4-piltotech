package edu.uoc.pac4.data.di

import edu.uoc.pac4.data.datasources.LocalData
import edu.uoc.pac4.data.datasources.LocalDataSource
import edu.uoc.pac4.data.datasources.RemoteData
import edu.uoc.pac4.data.datasources.RemoteDataSource
import edu.uoc.pac4.data.network.Network
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.oauth.OAuthAuthenticationRepository
import edu.uoc.pac4.data.streams.StreamsRepository
import edu.uoc.pac4.data.streams.TwitchStreamsRepository
import edu.uoc.pac4.data.user.TwitchUserRepository
import edu.uoc.pac4.data.user.UserRepository
import io.ktor.client.*
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

/**
 * Created by alex on 11/21/20.
 */

val dataModule = module {

    single<LocalData>(StringQualifier("local_data")) { LocalDataSource(get()) }
    single<RemoteData>(StringQualifier("remote_data")) {
        val http = Network.createHttpClient(get())
        RemoteDataSource(http) }


    single<AuthenticationRepository> {
        OAuthAuthenticationRepository(get(StringQualifier("local_data")), get(StringQualifier("remote_data"))) }

    single<StreamsRepository> {
        TwitchStreamsRepository(get(StringQualifier("local_data")), get(StringQualifier("remote_data"))) }

    single<UserRepository> {
        TwitchUserRepository(get(StringQualifier("local_data")), get(StringQualifier("remote_data"))) }
}