package com.diegohr.ejercicio_bitsa_bitnami.model

import javax.inject.Inject

/**
 * Created by Diego Hernando on 9/6/21.
 */
class GamePlayService @Inject constructor(private val castleStatusRepository: CastleStatusRepository) {

    fun playAllGame () : Array<WindowStatus> {
        var castleStatus = castleStatusRepository.initialCastleStatus()
        val visitors = (1..castleStatusRepository.castleNumWindows)
        visitors.forEach{
            castleStatus = play(it, castleStatus)
        }
        return castleStatus
    }

    fun play (positionVisitor : Int, castleStatus : Array<WindowStatus>) : Array<WindowStatus>{
        return when (positionVisitor){
            castleStatusRepository.castleNumWindows ->lastPlay(castleStatus)
            1 -> firstPlay(1, castleStatus)
            2 -> firstPlay(2, castleStatus)
            else -> normalPlay(positionVisitor, castleStatus)
        }
    }

    internal fun firstPlay(positionVisitor: Int, castleStatus: Array<WindowStatus>) : Array<WindowStatus>{
        val positionsToChange = genPositionsToModificate(positionVisitor, castleStatus)
        val windowOperation = calculateOpenWindowOperation(positionVisitor)
        return castleStatusRepository.changeListOfWindowStatusInCastleStatus(windowOperation, positionsToChange, castleStatus)
    }

    internal fun normalPlay (positionVisitor : Int, castleStatus: Array<WindowStatus>) : Array<WindowStatus>  {
        val positionsToChange = genPositionsToModificate(positionVisitor, castleStatus)
        val openWindowOperation = calculateOpenWindowOperation(positionVisitor)
        val closeWindowOperation = calculateCloseWindowOperation(positionVisitor)
        return castleStatusRepository.changeListOfWindowStatusInCastleStatus(openWindowOperation, positionsToChange,
            castleStatusRepository.changeListOfWindowStatusInCastleStatus(closeWindowOperation, positionsToChange, castleStatus))
    }

    internal fun lastPlay (castleStatus: Array<WindowStatus>) : Array<WindowStatus>  {
        val positions = genPositionsToModificate(1, castleStatus)
        val positionsToClose = positions.filter {
            val status = castleStatus[it-1]
            status == WindowStatus.OPEN || status == WindowStatus.RIGHT_OPEN
        }.toList()
        val positionsToOpen = positions.filter {
            val status = castleStatus[it-1]
            status != WindowStatus.OPEN && status != WindowStatus.RIGHT_OPEN
        }
        return castleStatusRepository.changeListOfWindowStatusInCastleStatus(WindowOperations.OPEN_RIGHT, positionsToOpen ,
                castleStatusRepository.changeListOfWindowStatusInCastleStatus(WindowOperations.CLOSE_RIGHT, positionsToClose, castleStatus)
        )
    }


    private fun genPositionsToModificate (positionVisitor: Int, castleStatus : Array<WindowStatus>) : List<Int>{
        return (1..castleStatus.size).filter { it % positionVisitor == 0 }.toList()
    }

    private fun calculateOpenWindowOperation (positionVisitor: Int): WindowOperations{
        if(positionVisitor % 2 == 0){
            return WindowOperations.OPEN_RIGHT
        }else{
            return WindowOperations.OPEN_LEFT
        }
    }

    private fun calculateCloseWindowOperation (positionVisitor: Int): WindowOperations{
        if(positionVisitor % 2 == 0){
            return WindowOperations.CLOSE_LEFT
        }else{
            return WindowOperations.CLOSE_RIGHT
        }
    }



}