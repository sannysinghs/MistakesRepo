package com.samples.simplekotlin

import android.content.Context
import com.google.gson.Gson
import com.samples.simplekotlin.data.model.Mistake
import com.samples.simplekotlin.data.model.MistakesResponse
import com.samples.simplekotlin.data.source.local.MistakesLocalDataSource
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class MistakesLocalDataSourceTest {

    @Before
    fun setup() {

    }

    @Test
    fun getAll_Test() {
        val mistakes = listOf(
                Mistake(title = "Do not forgot passport", id = "1", desc = "It was one fine early morning to changi airport to go back to yangon. Apperantely my passport was not with me when i reach airport . Because of this i spent additonal 50\$ just to ask @bala with taxi. As a result i learned something that i am so forgetful and i need to come up with checklist which would remind me before i went out to oversea . "),
                Mistake(title = "20$ waste at Ice skating", id = "2"),
                Mistake(title = "Online ticket to Batam cause me 50$ more", id = "3"),
                Mistake(title = "Pouring Jug", id = "4"),
                Mistake(title = "Check before you use force", id = "5", isFavourite = true, desc = "Today I made a big mistake . My faceshop bottle got cracked as I put it carelessly on the margin of my bed . Once I pulled out the bed , it rolled out and got cracked. As a result , i’ve learnt that whenever i use force i am urged to check whether there are stuffs around it .")
        )

        val toJson = Gson().toJson(MistakesResponse(mistakes = mistakes), MistakesResponse::class.java)

        Assert.assertEquals(mistakes, Gson().fromJson(toJson, MistakesResponse::class.java).mistakes)
    }

}