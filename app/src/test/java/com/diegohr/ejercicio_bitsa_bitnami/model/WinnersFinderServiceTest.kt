package com.diegohr.ejercicio_bitsa_bitnami.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by Diego Hernando on 9/6/21.
 */
class WinnersFinderServiceTest {

    private val service = WinnersFinderService()

    private val castleStatusTest1 = listOf(
        "LEFT_OPEN","LEFT_OPEN","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
    ).map { WindowStatus.valueOf(it) }.toTypedArray()

    private val castleStatusTest2 = listOf(
        "OPEN","LEFT_OPEN","OPEN","CLOSED","OPEN","CLOSED","OPEN","RIGHT_OPEN",
    ).map { WindowStatus.valueOf(it) }.toTypedArray()

    private val castleStatusTest3 = listOf(
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","CLOSED","RIGHT_OPEN",
    ).map { WindowStatus.valueOf(it) }.toTypedArray()

    @Test
    fun `check list of winners of castleStatusTest1` () {
        val winnersTest = listOf(5,7)
        val winners = service.findWinners(castleStatusTest1)
        assertThat(winners, `is`(winnersTest))
    }

    @Test
    fun `check list of second winners of castleStatusTest1` () {
        val winnersTest = listOf(3,5,7)
        val winners = service.findSecondWinners(castleStatusTest1)
        assertThat(winners, `is`(winnersTest))
    }

    @Test
    fun `check list of winners of castleStatusTest2` () {
        val winnersTest = listOf(5)
        val winners = service.findWinners(castleStatusTest2)
        assertThat(winners, `is`(winnersTest))
    }

    @Test
    fun `check list of second winners of castleStatusTest2` () {
        val winnersTest = listOf(1,3,5,7)
        val winners = service.findSecondWinners(castleStatusTest2)
        assertThat(winners, `is`(winnersTest))
    }

    @Test
    fun `check list of winners of castleStatusTest3` () {
        val winnersTest = listOf(1,3,5)
        val winners = service.findWinners(castleStatusTest3)
        assertThat(winners, `is`(winnersTest))
    }

    @Test
    fun `check list of second winners of castleStatusTest3` () {
        val winnersTest = listOf(1,3,5)
        val winners = service.findSecondWinners(castleStatusTest3)
        assertThat(winners, `is`(winnersTest))
    }

}