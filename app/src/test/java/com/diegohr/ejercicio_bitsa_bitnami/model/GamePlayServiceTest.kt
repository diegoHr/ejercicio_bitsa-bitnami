package com.diegohr.ejercicio_bitsa_bitnami.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by Diego Hernando on 9/6/21.
 */
class GamePlayServiceTest {
    private val castleStatusRepository = CastleStatusRepository(WindowUtils(), 64)
    private val service = GamePlayService(castleStatusRepository)

    private val castleStatusAfterThirdVisitorTestOk = listOf(
        "OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN",
        "LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN",
        "OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN",
        "OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN",
        "LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN",
        "OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN",
        "OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN",
        "LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN","OPEN","LEFT_OPEN","OPEN"
    ).map { WindowStatus.valueOf(it) }.toTypedArray()

    private val castleStatusAfterFourthVisitorTestOk = listOf(
        "OPEN","OPEN","LEFT_OPEN","RIGHT_OPEN","OPEN","LEFT_OPEN","OPEN","RIGHT_OPEN",
        "LEFT_OPEN","OPEN","OPEN","RIGHT_OPEN","OPEN","OPEN","LEFT_OPEN","RIGHT_OPEN",
        "OPEN","LEFT_OPEN","OPEN","RIGHT_OPEN","LEFT_OPEN","OPEN","OPEN","RIGHT_OPEN",
        "OPEN","OPEN","LEFT_OPEN","RIGHT_OPEN","OPEN","LEFT_OPEN","OPEN","RIGHT_OPEN",
        "LEFT_OPEN","OPEN","OPEN","RIGHT_OPEN","OPEN","OPEN","LEFT_OPEN","RIGHT_OPEN",
        "OPEN","LEFT_OPEN","OPEN","RIGHT_OPEN","LEFT_OPEN","OPEN","OPEN","RIGHT_OPEN",
        "OPEN","OPEN","LEFT_OPEN","RIGHT_OPEN","OPEN","LEFT_OPEN","OPEN","RIGHT_OPEN",
        "LEFT_OPEN","OPEN","OPEN","RIGHT_OPEN","OPEN","OPEN","LEFT_OPEN","RIGHT_OPEN"
    ).map { WindowStatus.valueOf(it) }.toTypedArray()

    private val castleStatusAfterAllGameTestOk = listOf(
        "LEFT_OPEN","LEFT_OPEN","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED",
        "OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED","OPEN","CLOSED"
    ).map { WindowStatus.valueOf(it) }.toTypedArray()


    @Test
    fun `check status castle after first visitor play not change anything`(){
        val statusAfterPlay = service.firstPlay(1, castleStatusRepository.initialCastleStatus())
        assertThat(statusAfterPlay, `is`(castleStatusRepository.initialCastleStatus()))
    }

    @Test
    fun `check status castle after second visitor play not change anything`(){
        val statusAfterFirstPlay = service.firstPlay(1, castleStatusRepository.initialCastleStatus())
        val statusAfterSecondPlay = service.firstPlay(2, statusAfterFirstPlay)
        assertThat(statusAfterSecondPlay, `is`(castleStatusRepository.initialCastleStatus()))
    }

    @Test
    fun `check status castle after third visitor`(){
        val statusAfterFirstPlay = service.firstPlay(1, castleStatusRepository.initialCastleStatus())
        val statusAfterSecondPlay = service.firstPlay(2, statusAfterFirstPlay)
        val statusAfterThirdPlay = service.normalPlay(3, statusAfterSecondPlay)
        assertThat(statusAfterThirdPlay, `is`(castleStatusAfterThirdVisitorTestOk))
    }

    @Test
    fun `check status castle after fourth visitor`(){
        val statusAfterFourthPlay = service.normalPlay(4, castleStatusAfterThirdVisitorTestOk)

        assertThat(statusAfterFourthPlay, `is`(castleStatusAfterFourthVisitorTestOk))
    }

    @Test
    fun `check status castle after last visitor`(){
        val statusAfterGame = service.playAllGame()

        assertThat(statusAfterGame, `is`(castleStatusAfterAllGameTestOk))
    }
}