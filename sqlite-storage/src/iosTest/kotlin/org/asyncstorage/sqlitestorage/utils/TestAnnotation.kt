package org.asyncstorage.sqlitestorage.utils

import kotlin.reflect.KClass

actual abstract class Runner

actual class JunitRunner : Runner()

actual annotation class RunWith(actual val value: KClass<out Runner>)
