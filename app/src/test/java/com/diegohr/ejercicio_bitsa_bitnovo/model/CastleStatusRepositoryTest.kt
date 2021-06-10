package com.diegohr.ejercicio_bitsa_bitnovo.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.fail
import org.junit.Test

/**
 * Created by Diego Hernando on 9/6/21.
 */
class CastleStatusRepositoryTest {

    private val repository = CastleStatusRepository(WindowUtils(), 64)
    private val initialCastleStatusTest = arrayOf(
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN",
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN",
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN",
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN",
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN",
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN",
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN",
        "OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN","OPEN").map{ WindowStatus.valueOf(it)}.toTypedArray()


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

    @Test
    fun `check change some window status that are in castle` () {
        val positions = listOf(1,64,20,10)
        val modifiedCastleStatus = repository.changeListOfWindowStatusInCastleStatus(WindowOperations.CLOSE_RIGHT, positions, initialCastleStatusTest)
        modifiedCastleStatus.forEachIndexed { index, s ->
            val position = index + 1
            if(s == WindowStatus.OPEN){
               assertThat(positions.contains(position), `is`(false))
            }else if (s == WindowStatus.LEFT_OPEN){
                assertThat(positions.contains(position), `is`(true))
            }else{
                fail("Readed window state not covered ($s) at $position position")
            }
        }
    }

    @Test
    fun `check change window status that someone is not in castle` () {
        val positions = listOf(1,64,20,65)
        try {
            repository.changeListOfWindowStatusInCastleStatus(
                WindowOperations.CLOSE_RIGHT,
                positions,
                initialCastleStatusTest
            )
            fail("Exception not thrown on exceeding array size")
        }catch (th : Throwable){
            assertThat(th is IndexOutOfBoundsException, `is`(true))
        }
    }


    private fun checkInternalLimitsChangingWindowStatusofCasteStatus (position: Int){
        val modifiedCastleStatus = repository.changeWindowStatusInCastleStatus(WindowOperations.CLOSE_RIGHT, position, initialCastleStatusTest)
        assertThat(modifiedCastleStatus[position-1], `is`(WindowStatus.LEFT_OPEN))
        val modifiedCastleStatusOkTest = initialCastleStatusTest.copyOf()
        modifiedCastleStatusOkTest[position-1] = WindowStatus.LEFT_OPEN
        assertThat(modifiedCastleStatus, `is`(modifiedCastleStatusOkTest))
    }

    private fun checkOuterLimitsChangingWindowStatusOfCastleStatus (position : Int) {
        try {
            repository.changeWindowStatusInCastleStatus(
                WindowOperations.CLOSE_RIGHT,
                position,
                initialCastleStatusTest
            )
            fail("Exception not thrown on exceeding array size")
        }catch (th : Throwable){
            assertThat(th is IndexOutOfBoundsException, `is`(true))
        }
    }

}