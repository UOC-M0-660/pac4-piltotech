package edu.uoc.pac4.data.oauth

import java.util.*

/**
 * Created by alex on 07/09/2020.
 */
object OAuthConstants {

    // OAuth2 Variables
    const val clientID = "adfy46v3qrqnwfdyzghtuskcek3zrc"
    const val redirectUri = "http://localhost"
    val scopes = listOf("user:read:email user:edit")
    val uniqueState = UUID.randomUUID().toString()
    const val clientSecret = "pejuvtdxaebb1rbgd9v44c7psqp134"

}