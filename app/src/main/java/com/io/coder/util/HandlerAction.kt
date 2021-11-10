package com.io.coder.util


interface HandlerAction<T> {

    fun listener(action: T)
}