package com.diegohr.ejercicio_bitsa_bitnami.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.fail
import org.junit.Test

/**
 * Created by Diego Hernando on 9/6/21.
 */
class CastleStatusRepositoryTest {

    private val repository = CastleStatusRepository()
    private val initialCastleStatusTest = arrayOf(
        "A","A","A","A","A","A","A","A",
        "A","A","A","A","A","A","A","A",
        "A","A","A","A","A","A","A","A",
        "A","A","A","A","A","A","A","A",
        "A","A","A","A","A","A","A","A",
        "A","A","A","A","A","A","A","A",
        "A","A","A","A","A","A","A","A",
        "A","A","A","A","A","A","A","A")


    @Test
    fun `check initial castle status is an array with 64 windows opened` () {
        assertThat(repository.initialCastleStatus(), `is`(initialCastleStatusTest))
    }

    @Test
    fun `check change window status of window 64` (){
        checkInternalLimitsChangingWindowStatusofCasteStatus(64)
    }

    @Test
    fun `check change window status of window 1` (){
        checkInternalLimitsChangingWindowStatusofCasteStatus(1)
    }

    @Test
    fun `check change window status of window 65 throws IndexOutOfBoundsException` (){
        checkOuterLimitsChangingWindowStatusOfCastleStatus(65)
    }

    @Test
    fun `check change window status of window 0 throws IndexOutOfBoundsException` (){
        checkOuterLimitsChangingWindowStatusOfCastleStatus(0)
    }


    private fun checkInternalLimitsChangingWindowStatusofCasteStatus (position: Int){
        val modifiedCastleStatus = repository.changeWindowStatusOfCastleStatus(WindowStatus.CLOSED, position, initialCastleStatusTest)
        assertThat(modifiedCastleStatus[position-1], `is`(WindowStatus.CLOSED.status))
        val modifiedCastleStatusOkTest = initialCastleStatusTest.copyOf()
        modifiedCastleStatusOkTest[position-1] = WindowStatus.CLOSED.status
        assertThat(modifiedCastleStatus, `is`(modifiedCastleStatusOkTest))
    }

    private fun checkOuterLimitsChangingWindowStatusOfCastleStatus (position : Int) {
        try {
            repository.changeWindowStatusOfCastleStatus(
                WindowStatus.CLOSED,
                position,
                initialCastleStatusTest
            )
            fail("Exception not thrown on exceeding array size")
        }catch (th : Throwable){
            assertThat(th is IndexOutOfBoundsException, `is`(true))
        }
    }

}