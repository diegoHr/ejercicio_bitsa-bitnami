package com.diegohr.ejercicio_bitsa_bitnovo.model


import javax.inject.Inject
import kotlin.jvm.Throws

/**
 * Created by Diego Hernando on 9/6/21.
 */
class CastleStatusRepository  constructor (private val windowUtils : WindowUtils,
                                                  val castleNumWindows : Int) {

    @Inject
    constructor(windowUtils: WindowUtils) : this (windowUtils, GameConstants.CASTLE_NUM_WINDOWS)


    fun initialCastleStatus () : Array<WindowStatus>{
        val sequence = generateSequence {
            WindowStatus.OPEN
        }
        return sequence.take(castleNumWindows).toList().toTypedArray()
    }


    /**
     * position param is the index of the window in castle status, starting in position 1.
     */
    @Throws (IndexOutOfBoundsException::class)
    fun changeWindowStatusInCastleStatus (operation: WindowOperations, position : Int,
                                          castleStatus : Array<WindowStatus>)
    : Array<WindowStatus>{
        checkPosition(position, castleStatus)
        val newCastleStatus = castleStatus.copyOf()
        newCastleStatus[position-1] = windowUtils.executeWindowOperation(operation, newCastleStatus[position-1])
        return newCastleStatus
    }

    fun changeListOfWindowStatusInCastleStatus (operation: WindowOperations, positions : List<Int>,
                                                castleStatus : Array<WindowStatus>) : Array<WindowStatus>{
        positions.forEach { checkPosition(it, castleStatus) }
        val newCastleStatus = castleStatus.copyOf()
        positions.forEach {
            newCastleStatus[it - 1] = windowUtils.executeWindowOperation(operation, newCastleStatus[it-1])
        }
        return newCastleStatus
    }

    @Throws (IndexOutOfBoundsException::class)
    private fun checkPosition (position: Int, castleStatus: Array<WindowStatus>){
        if(position > castleStatus.size || position < 0){
            throw IndexOutOfBoundsException()
        }
    }
}
