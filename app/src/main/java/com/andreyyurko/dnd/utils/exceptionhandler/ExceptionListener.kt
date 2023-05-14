package com.andreyyurko.dnd.utils.exceptionhandler


interface ExceptionListener {
    fun uncaughtException(thread: Thread, throwable: Throwable)
}