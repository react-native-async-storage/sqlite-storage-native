@file:OptIn(ExperimentalForeignApi::class)

package org.asyncstorage.sqlitestorage.utils

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import platform.Foundation.NSFileManager
import platform.Foundation.NSString
import platform.Foundation.writeToFile

@Suppress("CAST_NEVER_SUCCEEDS")
actual class FileTestHelper actual constructor(path: String) {
    private val file = path

    actual companion object {
        private val fm = NSFileManager.defaultManager

        actual fun createFileAt(
            path: String,
            rmIfExists: Boolean,
        ): FileTestHelper {
            val dbDir =
                path.split("/").run {
                    subList(0, lastIndex)
                        .joinToString("/")
                }

            if (rmIfExists) {
                memScoped {
                    val exists = fm.fileExistsAtPath(dbDir, alloc(true).ptr)
                    if (exists) {
                        fm.removeItemAtPath(dbDir, null)
                    }
                }
            }

            fm.createDirectoryAtPath(dbDir, true, null, null)
            fm.createFileAtPath(path, null, null)
            return FileTestHelper(path)
        }
    }

    actual fun fillWithChars(charCount: Int, char: String) {
        val content = char.repeat(charCount)
        try {
            (content as NSString).writeToFile(file, true)
        } catch (e: Throwable) {
        }
    }

    actual fun exists(): Boolean {
        return fm.fileExistsAtPath(file)
    }

    actual fun delete() {
        try {
            fm.removeItemAtPath(file, null)
        } catch (e: Throwable) {
        }
    }
}
