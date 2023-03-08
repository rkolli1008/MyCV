package com.exercise.cvapp

import androidx.lifecycle.LiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.data.ProfileDatabase
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ProfileDaoTest {

  @Inject
  lateinit var database: ProfileDatabase

  @Before
  fun init() {

  }

  @Test
  fun insertAndReadTest() {
    val contact = com.example.data.models.Contact("kolli.raghava.reddy@gmail.com", "", "")
    val skills = com.example.data.models.Skills("", "", "", "")
    val profile = com.example.data.models.Profile(
      "Raghava Kolli",
      "",
      "Vancouver, British Columbia, Canada",
      contact,
      "",
      emptyList(),
      emptyList(),
      skills
    )

    database.profileDao.insertAll(profile)

    database.profileDao.getProfileInRoom().observeOnce {
      assertThat(it.name, `is`("Raghava Kolli"))
      assertThat(it.location, `is`("Vancouver, British Columbia, Canada"))
      assertThat(it.contact?.email, `is`("kolli.raghava.reddy@gmail.com"))
    }
  }

  @After
  fun close() {
    database.close()
  }

  private fun <T> LiveData<T>.observeOnce(onChangeHandler: (T) -> Unit) {
    val observer = OneTimeObserver(handler = onChangeHandler)
    observe(observer, observer)
  }
}
