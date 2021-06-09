package com.diegohr.ejercicio_bitsa_bitnami.model

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by Diego Hernando on 9/6/21.
 */
class WindowUtilsTest {
    val utils = WindowUtils()

    @Test
    fun `check open left wing in opened window return opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_LEFT, WindowStatus.OPEN), `is`(WindowStatus.OPEN))
    }

    @Test
    fun `check open left wing in right opened window return opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_LEFT, WindowStatus.RIGHT_OPEN), `is`(WindowStatus.OPEN))
    }

    @Test
    fun `check open left wing in left opened window return left opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_LEFT, WindowStatus.LEFT_OPEN), `is`(WindowStatus.LEFT_OPEN))
    }

    @Test
    fun `check open left wing in closed window return left opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_LEFT, WindowStatus.CLOSED), `is`(WindowStatus.LEFT_OPEN))
    }

    @Test
    fun `check open right wing in opened window return opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_RIGHT, WindowStatus.OPEN), `is`(WindowStatus.OPEN))
    }

    @Test
    fun `check open right wing in left opened window return opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_RIGHT, WindowStatus.LEFT_OPEN), `is`(WindowStatus.OPEN))
    }

    @Test
    fun `check open right wing in right opened window return right opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_RIGHT, WindowStatus.RIGHT_OPEN), `is`(WindowStatus.RIGHT_OPEN))
    }

    @Test
    fun `check open right wing in closed window return right opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.OPEN_RIGHT, WindowStatus.CLOSED), `is`(WindowStatus.RIGHT_OPEN))
    }

    @Test
    fun `check close right wing in opened window return left opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_RIGHT, WindowStatus.OPEN), `is`(WindowStatus.LEFT_OPEN))
    }

    @Test
    fun `check close right wing in left opened window return left opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_RIGHT, WindowStatus.LEFT_OPEN), `is`(WindowStatus.LEFT_OPEN))
    }

    @Test
    fun `check close right wing in right opened window return closed window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_RIGHT, WindowStatus.RIGHT_OPEN), `is`(WindowStatus.CLOSED))
    }

    @Test
    fun `check close right wing in closed window return closed  window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_RIGHT, WindowStatus.CLOSED), `is`(WindowStatus.CLOSED))
    }


    @Test
    fun `check close left wing in opened window return right opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_LEFT, WindowStatus.OPEN), `is`(WindowStatus.RIGHT_OPEN))
    }

    @Test
    fun `check close left wing in left opened window return closed  window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_LEFT, WindowStatus.LEFT_OPEN), `is`(WindowStatus.CLOSED))
    }

    @Test
    fun `check close left wing in right opened window return right opened window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_LEFT, WindowStatus.RIGHT_OPEN), `is`(WindowStatus.RIGHT_OPEN))
    }

    @Test
    fun `check close left wing in closed window return closed  window` (){
        assertThat(utils.executeWindowOperation(WindowOperations.CLOSE_LEFT, WindowStatus.CLOSED), `is`(WindowStatus.CLOSED))
    }


}