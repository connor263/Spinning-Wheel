package com.picsart.stud.interfaces

interface PasteBinRepository {
    suspend fun fetch(callback: (String, Boolean) -> Unit)
}