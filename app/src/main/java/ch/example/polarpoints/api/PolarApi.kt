package ch.example.polarpoints.api

import android.util.Log
import ch.example.polarpoints.api.adapters.LocalDateAdapter
import ch.example.polarpoints.api.adapters.LocalTimeAdapter
import ch.example.polarpoints.api.model.Exercises
import ch.example.polarpoints.api.model.Transaction

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
        val profileUrl = "https://www.polaraccesslink.com/v3/users/%s"
        Log.i(LOG_TAG, String.format( "Now we're going to access the user profile for %s", accessToken!!.userId))
        val getRequest = OAuthRequest(
            Verb.GET,
            String.format(profileUrl, accessToken!!.userId)
        )
        getRequest.addHeader("x-li-format", "json")
        service!!.signRequest(accessToken, getRequest)
        val getResponse = service!!.execute(getRequest)
        Log.i(LOG_TAG, "get user HTTP code: " + getResponse.code)
        if(getResponse.code == 200){
            Log.i(LOG_TAG, "get user HTTP body: " + getResponse.body)
        }
        if(getResponse.code == 204){
            val putRequest = OAuthRequest(
                Verb.POST,
                String.format("https://www.polaraccesslink.com/v3/users/", accessToken!!.userId))
            putRequest.addHeader("Content-Type", "application/json;charset=UTF-8")
            putRequest.setPayload(String.format("{\"member-id\":\"%s\"}",accessToken!!.userId))
            service!!.signRequest(accessToken, putRequest)
            val putResponse = service!!.execute(putRequest)
            Log.i(LOG_TAG, "put user HTTP code: " + putResponse.code)
            Log.i(LOG_TAG, "put user HTTP body: " + putResponse.body)
        }
    }

    fun createTransaction() : Transaction? {
        val transactionUrl = String.format(
            "https://www.polaraccesslink.com/v3/users/%s/exercise-transactions",accessToken!!.userId)
        Log.i(LOG_TAG, "Now we're going to create a transaction...")
        val request = OAuthRequest(
            Verb.POST,
            String.format(transactionUrl, accessToken!!.userId)
        )
        request.addHeader("x-li-format", "json")
        service!!.signRequest(accessToken, request)
        val response = service!!.execute(request)
        Log.i(LOG_TAG, "create transaction result code: " + response.code)
        Log.i(LOG_TAG, "create transaction result body: " + response.body)
        val gson = getGson()
        return gson.fromJson(response.body, Transaction::class.java)
    }

    fun listExercisesInTransaction(transactionId : Int ): Exercises? {
        val transactionUrl = String.format(
            "https://www.polaraccesslink.com/v3/users/%s/exercise-transactions/%s",
                accessToken!!.userId, transactionId)
        Log.i(LOG_TAG, "Now we're going to access exercises In Transaction..")
        val request = OAuthRequest(
            Verb.GET,
            String.format(transactionUrl, accessToken!!.userId)
        )
        request.addHeader("x-li-format", "json")
        service!!.signRequest(accessToken, request)
        val response = service!!.execute(request)
        Log.i(LOG_TAG, "create transaction result code: " + response.code)
        Log.i(LOG_TAG, "create transaction result body: " + response.body)
        val gson = getGson()
        return gson.fromJson(response.body, Exercises::class.java)

    }

    fun getHeartRateZonesForExercise(transactionId : Int, exerciseId : Int){
        val transactionExerciseHRZUrl = String.format(
            "https://www.polaraccesslink.com/v3/users/%s/exercise-transactions/%s/exercises/%s/heart-rate-zones",
            accessToken!!.userId, transactionId, exerciseId)
        Log.i(LOG_TAG, "Now we're going to access exercises In Transaction..")
        val request = OAuthRequest(
            Verb.GET,
            String.format(transactionExerciseHRZUrl, accessToken!!.userId)
        )
        request.addHeader("x-li-format", "json")
        service!!.signRequest(accessToken, request)
        val response = service!!.execute(request)
        Log.i(LOG_TAG, "create transaction result code: " + response.code)
        Log.i(LOG_TAG, "create transaction result body: " + response.body)

    }
    private fun getGson() : Gson {
        return GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate::class.java, LocalDateAdapter())
            .registerTypeAdapter(LocalTime::class.java, LocalTimeAdapter())
            .create()
    }

}
