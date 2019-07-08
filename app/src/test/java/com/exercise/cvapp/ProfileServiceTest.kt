package com.exercise.cvapp

import com.exercise.cvapp.models.network.ProfileApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class ProfileServiceTest {
    @Throws(IOException::class)
    @Test
    fun fetchProfileTest() = runBlocking {
        val response = ProfileApi.RETROFIT_SERVICE.getProfileAsync().await()
        assertThat(response.name, `is`("Raghava Kolli"))
        assertThat(response.location, `is`("Vancouver, British Columbia, Canada"))
        assertThat(response.contact?.email, `is`("kolli.raghava.reddy@gmail.com"))
    }
}
