package bme.aut.sza.honfoglalo.domain.usecases

class QuizQuestUseCases (
    val loadUsernameUseCase: LoadUsernameUseCase,
    val joinGame: JoinGameUseCase,
    val lobbyWaitingUseCase: LobbyWaitingUseCase,
    val leaveGameUseCase: LeaveGameUseCase,
    val handleGameUseCase: HandleGameUseCase,
)