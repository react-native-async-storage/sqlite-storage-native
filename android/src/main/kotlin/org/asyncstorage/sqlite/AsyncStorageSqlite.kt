package org.asyncstorage.sqlite

import kotlin.random.Random


class AsyncStorageSqlite {
  companion object {
    const val NAME = "AsyncStorageSqlite"
  }

  fun random() = Random.nextInt(0, 100)
}