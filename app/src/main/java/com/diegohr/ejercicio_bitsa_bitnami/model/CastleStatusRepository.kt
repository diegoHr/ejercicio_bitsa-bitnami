package com.diegohr.ejercicio_bitsa_bitnami.model

import kotlin.jvm.Throws

/**
 * Created by Diego Hernando on 9/6/21.
 */
class CastleStatusRepository {

    fun initialCastleStatus () : Array<String>{
        val sequence = generateSequence {
            WindowStatus.OPENED.status
        }
        return sequence.take(64).toList().toTypedArray()
    }


    /**
     * position param is the index of the window in castle status, starting in position 1.
     */
    @Throws (IndexOutOfBoundsException::class)
    fun changeWindowStatusOfCastleStatus (newWinStatus : WindowStatus, position : Int,
                                          castleStatus : Array<String>)
    : Array<String>{
        if(position > castleStatus.size || position < 0){
            throw IndexOutOfBoundsException()
        }

        val newCastleStatus = castleStatus.copyOf()
        newCastleStatus[position-1] = newWinStatus.status

        return newCastleStatus
    }
}