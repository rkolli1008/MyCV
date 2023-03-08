package com.example.data.network

import com.example.data.models.Profile
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://storage.googleapis.com/mycv_bucket/"

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
interface ProfileApi {
    /**
     * Returns a Coroutine [Deferred] [Profile] which can be fetched with await() if
     * in a Coroutine scope.
     * The @GET annotation indicates that the "profile" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("profile.json")
    fun getProfileAsync(): Deferred<Profile>
}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object ProfileNetworkService {
    val RETROFIT_SERVICE: ProfileApi by lazy { retrofit.create(ProfileApi::class.java) }
}
