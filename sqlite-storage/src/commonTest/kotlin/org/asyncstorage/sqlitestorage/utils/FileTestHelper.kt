package org.asyncstorage.sqlitestorage.utils

expect class FileTestHelper(path: String) {
    companion object {
        fun createFileAt(
            path: String,
            rmIfExists: Boolean = true,
        ): FileTestHelper
    }

    fun fillWithChars(charCount: Int)

    fun exists(): Boolean

    fun delete()
}
