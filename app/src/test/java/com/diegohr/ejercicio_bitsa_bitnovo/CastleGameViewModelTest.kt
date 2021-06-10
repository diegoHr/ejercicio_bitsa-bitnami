package com.diegohr.ejercicio_bitsa_bitnovo

import com.diegohr.ejercicio_bitsa_bitnovo.model.CastleStatusRepository
import com.diegohr.ejercicio_bitsa_bitnovo.model.GamePlayService
import com.diegohr.ejercicio_bitsa_bitnovo.model.WindowUtils
import com.diegohr.ejercicio_bitsa_bitnovo.model.WinnersFinderService
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * Created by Diego Hernando on 10/6/21.
 */
class CastleGameViewModelTest {
    private val castleStatusRepository = CastleStatusRepository(WindowUtils(), 64)
    private val gamePlayService = GamePlayService(castleStatusRepository)
    private val winnersFinderService = WinnersFinderService()
    private val viewModel = CastleGameViewModel(gamePlayService, winnersFinderService, castleStatusRepository)


    @Test
    fun `check printList return empty string when list is empty`(){
        assertThat(viewModel.printList(listOf<String>()), `is`(""))

    }

    @Test
    fun `check printList return elements separated by commas`(){
        assertThat(viewModel.printList(listOf(1,2,3,4)), `is`("1, 2, 3, 4 "))

    }

}