package com.exercise.cvapp.network

import com.exercise.cvapp.models.Profile
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://gist.githubusercontent.com/rkolli1008/f9717e4ff9eb0b36037fede54fee153e/raw/5dfdd476db7431fd0213f7a0fc6bcf8a3b415039/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

/**
 * A public interface that exposes the [getProfileAsync] method
 */
interface GistApiService {
    /**
     * Returns a Coroutine [Deferred] [Profile] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "profile" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("profile.json")
    fun getProfileAsync():
    // The Coroutine Call Adapter allows us to return a Deferred, a Job with a result
            Deferred<Profile>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ProfileApi {
    val retrofitService : GistApiService by lazy { retrofit.create(GistApiService::class.java) }
}
