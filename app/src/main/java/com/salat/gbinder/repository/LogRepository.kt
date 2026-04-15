package com.salat.gbinder.repository

interface LogRepository {
    fun log(msg: String)

    fun deepLog(msg: String)
}
