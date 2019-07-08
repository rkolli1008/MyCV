package com.exercise.cvapp

import androidx.lifecycle.LiveData
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.exercise.cvapp.database.ProfileDatabase
import com.exercise.cvapp.database.getDatabase
import com.exercise.cvapp.models.Contact
import com.exercise.cvapp.models.Profile
import com.exercise.cvapp.models.Skills
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProfileDaoTest {

  lateinit var database: ProfileDatabase

  @Before
  fun init() {
    database = getDatabase(InstrumentationRegistry.getInstrumentation().context)
  }

  @Test
  fun insertAndReadTest() {
    val contact = Contact("kolli.raghava.reddy@gmail.com", "", "")
    val skills = Skills("", "", "", "")
    val profile = Profile("Raghava Kolli", "", "Vancouver, British Columbia, Canada", contact, "", emptyList(), emptyList(), skills)

    database.profileDao.insertAll(profile)

    database.profileDao.getProfile().observeOnce {
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
