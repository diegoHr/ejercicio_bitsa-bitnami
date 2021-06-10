package com.diegohr.ejercicio_bitsa_bitnovo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegohr.ejercicio_bitsa_bitnovo.model.CastleStatusRepository
import com.diegohr.ejercicio_bitsa_bitnovo.model.GamePlayService
import com.diegohr.ejercicio_bitsa_bitnovo.model.WindowStatus
import com.diegohr.ejercicio_bitsa_bitnovo.model.WinnersFinderService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Diego Hernando on 9/6/21.
 */
@HiltViewModel
class CastleGameViewModel @Inject constructor(private val gamePlayService: GamePlayService,
                                              private val winnersFinderService: WinnersFinderService,
                                              private val castleStatusRepository: CastleStatusRepository)
    : ViewModel(){

    var castleStatus : Array<WindowStatus> = arrayOf()
    private set

    var winners : List<Int> = listOf()
    private set

    var secondWinners : List<Int> = listOf()
    private set

    val errorMessage = MutableLiveData<String>(null)
    val isGameFinished = MutableLiveData<Boolean> (false)

    var numWindowsOpened = 0
    private set

    var numWindowsClosed = 0
    private set

    var numWindowsRightOpened = 0
    private set

    var numWindowsLeftOpened = 0
    private set


    fun initGame () {
        viewModelScope.launch {
            try{
                isGameFinished.value = false
                castleStatus = withContext(Dispatchers.Default){
                    delay(700) //Improve UI quality
                    if(castleStatus.size == 0){
                        castleStatus = castleStatusRepository.initialCastleStatus()
                    }
                    gamePlayService.playAllGame(castleStatus)
                }

                numWindowsOpened = countTypeWindowStatus(castleStatus, WindowStatus.OPEN)
                numWindowsClosed = countTypeWindowStatus(castleStatus, WindowStatus.CLOSED)
                numWindowsRightOpened = countTypeWindowStatus(castleStatus, WindowStatus.RIGHT_OPEN)
                numWindowsLeftOpened = countTypeWindowStatus(castleStatus, WindowStatus.LEFT_OPEN)

                winners = withContext(Dispatchers.Default){
                    winnersFinderService.findWinners(castleStatus)
                }
                secondWinners = withContext(Dispatchers.Default){
                    winnersFinderService.findSecondWinners(castleStatus)
                }

                isGameFinished.value = true
            }catch (th : Throwable){
                errorMessage.value = th.message
            }
        }
    }


    fun reset (){
        viewModelScope.launch {
            try{
                isGameFinished.value = false
                castleStatus = withContext(Dispatchers.Default){
                    castleStatusRepository.initialCastleStatus()
                }
                winners = listOf()
                secondWinners = listOf()
                numWindowsOpened = 0
                numWindowsClosed = 0
                numWindowsRightOpened = 0
                numWindowsLeftOpened = 0

                isGameFinished.value = true
            }catch (th : Throwable){
                errorMessage.value = th.message
            }
        }
    }

    fun printListWinners () : String {
        return printList(winners)
    }

    fun printListSecondWinners () : String {
        return printList(secondWinners)
    }

    internal fun <E> printList (list : List<E>) : String{
        val listStringBuilder = StringBuilder ()
        list.forEach {
            listStringBuilder.append("${it.toString()}, ")
        }
        if(listStringBuilder.count() >= 2) {
            listStringBuilder.deleteCharAt(listStringBuilder.count() - 2) // delete last comma
        }
        return listStringBuilder.toString()
    }

    private suspend fun countTypeWindowStatus (castleStatus : Array<WindowStatus>, windowStatusFilter: WindowStatus) = withContext(Dispatchers.Default) {
        castleStatus.filter { it == windowStatusFilter }.count()
    }

}