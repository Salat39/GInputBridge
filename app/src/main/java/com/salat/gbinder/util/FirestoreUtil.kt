package com.salat.gbinder.util

import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.Source
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withTimeout
import java.util.concurrent.TimeUnit

sealed interface KvResult<out T> {
    data class Success<T>(
        val value: T,
        val fromCache: Boolean,
        val hasPendingWrites: Boolean
    ) : KvResult<T>

    data object NotFound : KvResult<Nothing>
    data class Failure(val error: Throwable) : KvResult<Nothing>
}

private const val COLLECTION = "kv"

suspend fun saveFirestoreValue(
    key: String,
    value: String,
    timeoutMs: Long = TimeUnit.SECONDS.toMillis(10)
): KvResult<Unit> {
    val db = Firebase.firestore
    return try {
        withTimeout(timeoutMs) {
            val payload = mapOf(
                "value" to value,
                "updatedAt" to FieldValue.serverTimestamp()
            )
            db.collection(COLLECTION).document(key).set(payload).await()
            KvResult.Success(Unit, fromCache = false, hasPendingWrites = false)
        }
    } catch (t: Throwable) {
        KvResult.Failure(t)
    }
}

suspend fun getLatestFirestoreValue(
    key: String,
    serverOnly: Boolean = false,
    timeoutMs: Long = TimeUnit.SECONDS.toMillis(10)
): KvResult<String> {
    val db = Firebase.firestore
    val source = if (serverOnly) Source.SERVER else Source.DEFAULT
    return try {
        val snap = withTimeout(timeoutMs) {
            db.collection(COLLECTION).document(key).get(source).await()
        }
        if (!snap.exists()) {
            KvResult.NotFound
        } else {
            val value = snap.getString("value")
            if (value == null) {
                KvResult.Failure(IllegalStateException("Field 'value' is not a String"))
            } else {
                KvResult.Success(
                    value = value,
                    fromCache = snap.metadata.isFromCache,
                    hasPendingWrites = snap.metadata.hasPendingWrites()
                )
            }
        }
    } catch (t: Throwable) {
        KvResult.Failure(t)
    }
}
