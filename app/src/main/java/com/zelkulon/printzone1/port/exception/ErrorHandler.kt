package com.zelkulon.printzone1.port.exception

object ErrorHandler {
    /**
     * Wandelt eine Exception in eine für den Nutzer verständliche Fehlermeldung um.
     */
    fun getUserFriendlyMessage(error: Throwable): String {
        return when(error) {
            is AppException.NetworkException -> "Keine Internetverbindung. Bitte versuchen Sie es später erneut."
            is AppException.DatabaseException -> "Interner Fehler beim Zugriff auf lokale Daten."
            is AppException.ValidationException -> error.message ?: "Ungültige Eingabe."
            else -> "Ein unbekannter Fehler ist aufgetreten."
        }
    }
}