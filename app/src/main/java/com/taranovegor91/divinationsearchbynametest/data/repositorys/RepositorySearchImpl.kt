package com.taranovegor91.divinationsearchbynametest.data.repositorys

import androidx.core.net.toUri
import com.taranovegor91.divinationsearchbynametest.domain.interfaces.repository.RepositorySearch
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.coroutines.resume

class RepositorySearchImpl : RepositorySearch {
    companion object {
        val BASE_URL = "https://api.agify.io/"
        val PARAM_NAME = "name"
        val JSONOBJECT_NAME = "age"
    }

    override suspend fun getDivination(name: String): String {
        var divi = ""
        val uri = BASE_URL.toUri().buildUpon().appendQueryParameter(PARAM_NAME, name).build()
        suspendCancellableCoroutine { continuation ->
            val request = Request.Builder()
                .url(uri.toString())
                .get().build()
            val client = OkHttpClient.Builder().build()
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    divi = ""
                    continuation.resume(divi)
                }

                override fun onResponse(call: Call, response: Response) {
                    response.body.use { responseBody ->
                        val jsonObject = JSONObject(responseBody!!.string())
                        divi = jsonObject.getString(JSONOBJECT_NAME)
                    }
                    continuation.resume(divi)
                }
            })
        }
        return divi
    }
}