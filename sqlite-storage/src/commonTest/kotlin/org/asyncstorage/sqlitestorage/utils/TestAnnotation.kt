package org.asyncstorage.sqlitestorage.utils

import kotlin.reflect.KClass

expect abstract class Runner

expect class JunitRunner : Runner

expect annotation class RunWith(val value: KClass<out Runner>)
