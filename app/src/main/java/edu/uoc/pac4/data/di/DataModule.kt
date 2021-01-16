package edu.uoc.pac4.data.di

import edu.uoc.pac4.data.SessionManager
import edu.uoc.pac4.data.network.Network
import edu.uoc.pac4.data.oauth.AuthenticationData
import edu.uoc.pac4.data.oauth.AuthenticationDataSource
import edu.uoc.pac4.data.oauth.AuthenticationRepository
import edu.uoc.pac4.data.oauth.OAuthAuthenticationRepository
import edu.uoc.pac4.data.streams.StreamsData
import edu.uoc.pac4.data.streams.StreamsDataSource
import edu.uoc.pac4.data.streams.StreamsRepository
import edu.uoc.pac4.data.streams.TwitchStreamsRepository
import edu.uoc.pac4.data.user.TwitchUserRepository
import edu.uoc.pac4.data.user.UserData
import edu.uoc.pac4.data.user.UserDataSource
import edu.uoc.pac4.data.user.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Created by alex on 11/21/20.
 */

val dataModule = module {

    single { Network.createHttpClient(androidContext()) }

    single { SessionManager(androidContext()) }

    single<AuthenticationData> { AuthenticationDataSource(get(), get()) }
    single<AuthenticationRepository> { OAuthAuthenticationRepository(get()) }

    single<StreamsData> { StreamsDataSource(get(), get()) }
    single<StreamsRepository> { TwitchStreamsRepository(get()) }

    single<UserData> { UserDataSource(get(), get()) }
    single<UserRepository> { TwitchUserRepository(get()) }
}