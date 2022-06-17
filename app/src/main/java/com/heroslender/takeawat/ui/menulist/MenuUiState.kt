package com.heroslender.takeawat.ui.menulist

sealed interface MenuUiState{
    object LoadingState : MenuUiState
    object ContentState : MenuUiState
    object DatesContentState : MenuUiState
    class ErrorState(val message: String) : MenuUiState
}
