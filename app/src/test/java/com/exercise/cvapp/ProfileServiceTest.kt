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
        /*val service = ProfileApi.RETROFIT_SERVICE
        val service1 = ProfileApi.RETROFIT_SERVICE
        val response = service.getProfileAsync().await()
        assertThat(response.name, `is`("Raghava Kolli"))
        assertThat(response.location, `is`("Vancouver, British Columbia, Canada"))
        assertThat(response.contact?.email, `is`("kolli.raghava.reddy@gmail.com"))*/
        game()
    }

    fun game() {
        val choices = arrayOf("Rock", "Paper", "Scissors")
        val gameChoice = getGameChoice(choices)
        val userChoice = getUserChoice(choices)
        if (gameChoice == "Scissors" && userChoice == "Scissors") {
            println("The Scissors choice wins, as Scissors can cut Paper.")
        } else if (gameChoice == "Rock" && userChoice == "Paper") {
            println("The Rock choice wins, as Rock can blunt Scissors.")
        } else if (gameChoice == "Paper" && userChoice == "Rock") {
            println("The Paper choice wins, as Paper can cover Rock.")
        }
    }

    private fun getGameChoice(gameChoices: Array<String>): String = gameChoices[(Math.random() * gameChoices.size).toInt()]

    private fun getUserChoice(gameChoices: Array<String>): String {
        print("Choose one option from following")

        for(item in gameChoices) {
            println(item)
        }

        var isRightChoice = false
        var userInput: String = ""
        while(!isRightChoice) {
            userInput = readLine().toString()
            if (userInput != null && userInput in gameChoices) {
                isRightChoice = true
            } else {
                println("Please eneter a valid choice")
            }
        }
        return userInput
    }
}
