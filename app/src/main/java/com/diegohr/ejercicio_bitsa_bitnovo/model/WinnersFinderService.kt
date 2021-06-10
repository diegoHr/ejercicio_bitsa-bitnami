package com.diegohr.ejercicio_bitsa_bitnovo.model

import javax.inject.Inject

/**
 * Created by Diego Hernando on 9/6/21.
 */
class WinnersFinderService @Inject constructor() {


    fun findWinners (castleStatus : Array<WindowStatus>) : List<Int>{
        val winners = mutableListOf<Int>()
        castleStatus.forEachIndexed { index, windowStatus ->
            val position = index + 1
            if( isWinner(windowStatus, position, castleStatus)){
                winners.add(position)
            }
        }
        return winners
    }

    fun findSecondWinners (castleStatus : Array<WindowStatus>) : List<Int>{
        val winners = mutableListOf<Int>()
        castleStatus.forEachIndexed { index, windowStatus ->
            val position = index + 1
            if( windowStatus == WindowStatus.OPEN){
                winners.add(position)
            }
        }
        return winners
    }

    private fun isWinner(windowStatus: WindowStatus, position : Int, castleStatus: Array<WindowStatus>) : Boolean{
        val index = position - 1
        return when (position){
            1 -> isWinner(windowStatus, castleStatus[index+1])
            castleStatus.size -> isWinner(windowStatus, castleStatus[index-1])
            else -> isWinner(windowStatus, castleStatus[index-1], castleStatus[index+1])
        }
    }

    private fun isWinner(possibleWinnerWinStat: WindowStatus, prevWinStat: WindowStatus, rearWindowStat : WindowStatus ) : Boolean{
        return possibleWinnerWinStat == WindowStatus.OPEN && prevWinStat == WindowStatus.CLOSED && rearWindowStat == WindowStatus.CLOSED
    }

    private fun isWinner(possibleWinnerWinStat: WindowStatus, nextWinStat: WindowStatus) : Boolean {
        return possibleWinnerWinStat == WindowStatus.OPEN && nextWinStat == WindowStatus.CLOSED

    }

}