package org.asyncstorage.sqlitestorage.utils

import java.io.File

actual class FileTestHelper actual constructor(path: String) {
    private val file = File(path)

    actual companion object {
        actual fun createFileAt(
            path: String,
            rmIfExists: Boolean,
        ): FileTestHelper {
            val file = File(path)
            val dbDir = file.parentFile!!
            if (rmIfExists) {
                if (dbDir.exists()) {
                    dbDir.deleteRecursively()
                }
            }
            dbDir.mkdirs()
            file.createNewFile()
            return FileTestHelper(path)
        }
    }

    actual fun fillWithChars(charCount: Int, char: String) {
        file.writeText(char.repeat(charCount))
    }

    actual fun exists() = file.exists()

    actual fun delete() {
        file.delete()
    }
}
