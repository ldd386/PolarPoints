package ch.example.polarpoints.api

import android.util.Log
import ch.example.polarpoints.api.adapters.LocalDateAdapter
import ch.example.polarpoints.api.adapters.LocalTimeAdapter

import com.github.scribejava.core.builder.ServiceBuilder
import com.github.scribejava.core.model.OAuthRequest
import com.github.scribejava.core.model.Verb
import com.github.scribejava.core.oauth.OAuth20Service
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime

class PolarApi {

    private val LOG_TAG = "PolarApi"

    private var service: OAuth20Service? = null
    private var accessToken: PolarOAuth2AccessToken? = null

    // Obtain the Authorization URL
    val authUrl: String
        get() {
            val clientId = "c22dee9b-607b-425b-8d5d-c6d80e96e7be"
            val clientSecret = "62a519dd-9f83-4cfa-8197-5b5288d427b2"
            service = ServiceBuilder(clientId)
                .apiSecret(clientSecret)
                .build(PolarApi20.instance())
            Log.i(LOG_TAG, "=== Polar's OAuth Workflow ===")
            Log.i(LOG_TAG, "Fetching the Authorization URL...")
            val authorizationUrl = service!!.authorizationUrl
            Log.i(LOG_TAG, "Got the Authorization URL!")
            Log.i(LOG_TAG, "Now go and authorize ScribeJava here:")
            Log.i(LOG_TAG, authorizationUrl)
            return authorizationUrl
        }

    fun doAuth(code: String) {
        // Trade the Request Token and Verfier for the Access Token
        Log.i(LOG_TAG, "Trading the Request Token for an Access Token...")
        val oauth2AccessToken = service!!.getAccessToken(code)
        Log.i(LOG_TAG, "Got the Access Token!")
        Log.i(
            LOG_TAG, "(if your curious it looks like this: " + oauth2AccessToken
                    + ", 'rawResponse'='" + oauth2AccessToken.rawResponse + "')"
        )
        if (oauth2AccessToken !is PolarOAuth2AccessToken) {
            Log.i(LOG_TAG, "oauth2AccessToken is not instance of PolarAuth2AccessToken. Strange enough. exit.")
            return
        }
        accessToken = oauth2AccessToken
    }

    fun loadProfile() {
        val profileUrl = "https://www.polaraccesslink.com/v3/users/42989986"
        // Now let's go and ask for a protected resource!
        // This will get the profile for this user
        Log.i(LOG_TAG, "Now we're going to access the user profile...")
        val request = OAuthRequest(
            Verb.GET,
            String.format(profileUrl, accessToken!!.userId)
        )
        request.addHeader("x-li-format", "json")
        service!!.signRequest(accessToken, request)
        val response = service!!.execute(request)
        Log.i(LOG_TAG, "get profile HTTP code: " + response.code)
        Log.i(LOG_TAG, "get profile HTTP body: " + response.body)
    }

    private fun getGson() : Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
            .create()
    }

}
