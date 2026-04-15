package com.salat.gbinder

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class BootContentProvider : ContentProvider() {
    private var deviceIsActive = false

    override fun onCreate(): Boolean {
        checkAndHandleBootSession()
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        throw UnsupportedOperationException("Not allowed")
    }

    override fun getType(uri: Uri): String? {
        throw UnsupportedOperationException("Not allowed")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        throw UnsupportedOperationException("Not allowed")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        throw UnsupportedOperationException("Not allowed")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        throw UnsupportedOperationException("Not allowed")
    }

    @SuppressLint("LogNotTimber")
    private fun checkAndHandleBootSession() {
        context?.apply {
            deviceIsActive = true
            println("[CP] Device is ready: $deviceIsActive")
        }
    }
}
