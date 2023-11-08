package org.asyncstorage.sqlite

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.module.annotations.ReactModule

@ReactModule(name = AsyncStorageSqlite.NAME)
class AsyncStorageSqliteModule : ReactContextBaseJavaModule() {
  override fun getName() = AsyncStorageSqlite.NAME

  private val storage = AsyncStorageSqlite()

  @ReactMethod
  fun getRandomNumber(promise: Promise) {
    promise.resolve(storage.random())
  }
}