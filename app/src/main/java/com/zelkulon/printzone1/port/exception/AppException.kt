package com.zelkulon.printzone1.port.exception

/** Basisklasse für applikationsspezifische Exceptions. */
sealed class AppException(message: String): Exception(message) {
    /** Netzwerkfehler (z.B. keine Verbindung, Timeout) */
    class NetworkException(cause: Throwable? = null): AppException("Netzwerkfehler") {
        init {
            initCause(cause)
        }
    }
    /** Datenbank-/Speicherfehler */
    class DatabaseException(cause: Throwable? = null): AppException("Datenbankfehler") {
        init {
            initCause(cause)
        }
    }
    /** Fehler durch ungültige Eingaben oder Anwendungslogik */
    class ValidationException(message: String): AppException(message)
}
