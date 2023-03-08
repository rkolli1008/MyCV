package com.exercise.cvapp

import com.example.data.models.network.ProfileNetworkService
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
        val service = com.example.data.models.network.ProfileNetworkService.RETROFIT_SERVICE
        val response = service.getProfileAsync().await()
        assertThat(response.name, `is`("Raghava Kolli"))
        assertThat(response.location, `is`("Vancouver, British Columbia, Canada"))
        assertThat(response.contact?.email, `is`("kolli.raghava.reddy@gmail.com"))
    }
}
