package com.diegohr.ejercicio_bitsa_bitnami.model

/**
 * Created by Diego Hernando on 9/6/21.
 */
class WindowUtils {

    fun executeWindowOperation (operation: WindowOperations, status: WindowStatus) : WindowStatus{
        return when(operation){
            WindowOperations.OPEN_LEFT -> openLeft(status)
            WindowOperations.OPEN_RIGHT -> openRight(status)
            WindowOperations.CLOSE_LEFT -> closeLeft(status)
            else -> closeRight(status)
        }
    }

    private fun openRight (status: WindowStatus) : WindowStatus{
        return when(status){
            WindowStatus.LEFT_OPEN -> WindowStatus.OPEN
            WindowStatus.RIGHT_OPEN -> WindowStatus.RIGHT_OPEN
            WindowStatus.CLOSED -> WindowStatus.RIGHT_OPEN
            else -> WindowStatus.OPEN
        }
    }

    private fun openLeft (status: WindowStatus) : WindowStatus {
        return when(status){
            WindowStatus.LEFT_OPEN -> WindowStatus.LEFT_OPEN
            WindowStatus.RIGHT_OPEN -> WindowStatus.OPEN
            WindowStatus.CLOSED -> WindowStatus.LEFT_OPEN
            else -> WindowStatus.OPEN
        }
    }

    private fun closeRight (status: WindowStatus) : WindowStatus {
        return when(status){
            WindowStatus.LEFT_OPEN -> WindowStatus.LEFT_OPEN
            WindowStatus.RIGHT_OPEN -> WindowStatus.CLOSED
            WindowStatus.CLOSED -> WindowStatus.CLOSED
            else -> WindowStatus.LEFT_OPEN
        }
    }

    private fun closeLeft (status: WindowStatus) : WindowStatus {
        return when(status){
            WindowStatus.LEFT_OPEN -> WindowStatus.CLOSED
            WindowStatus.RIGHT_OPEN -> WindowStatus.RIGHT_OPEN
            WindowStatus.CLOSED -> WindowStatus.CLOSED
            else -> WindowStatus.RIGHT_OPEN
        }
    }

}

enum class WindowOperations {
    OPEN_RIGHT, OPEN_LEFT, CLOSE_LEFT, CLOSE_RIGHT
}

enum class WindowStatus (val status : String){
    OPEN("A"), CLOSED("C"), LEFT_OPEN ("I"), RIGHT_OPEN("R");
}