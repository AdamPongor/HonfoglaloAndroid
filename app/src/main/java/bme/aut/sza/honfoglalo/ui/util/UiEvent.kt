package bme.aut.sza.honfoglalo.ui.util

import bme.aut.sza.honfoglalo.ui.model.UiText

sealed class UiEvent {
    data class Failure(val message: UiText) : UiEvent()
    object Success : UiEvent()
}