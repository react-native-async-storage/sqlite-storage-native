# Async Storage SQLite

Multiplatform key-value storage backed by SQLite for React Native Async Storage

![version badge](https://img.shields.io/badge/dynamic/json?url=https%3A%2F%2Fraw.githubusercontent.com%2Freact-native-async-storage%2Fsqlite-storage-native%2Fmain%2Fpackage_info.json&query=%24.version&style=flat&label=latest%20version&color=7956b7)

## Installation

### Android

```kotlin
implementation("io.github.react-native-async-storage:async-storage-sqlite:VERSION")
```

### iOS (CocoaPods)

```ruby
pod 'AsyncStorageSQLiteKMP', 'VERSION'
```

## Quick start

Visit [Usage page](https://react-native-async-storage.github.io/sqlite-storage-native/usage/) to learn more


### Android

```kotlin
import org.asyncstorage.sqlitestorage.SQLiteStorageFactory

val storage = SQLiteStorageFactory(this).create("my_database_name")

suspend fun single(): Entry {
  val entry = storage.read("my_key")
  return entry
}

suspend fun singleWrite() {
  val entry = Entry("my_key", "my_value")
  storage.write(entry)
}
```


### iOS

```swift
import AsyncStorageSQLite

let storage = AsyncStorageSQLite("my_database_name")

func single() async throws -> Entry {
    let entry = try await storage.read(key: "my_key")
    return entry
}

func singleWrite() async throws {
    let entry = Entry(key: "my_key", value: "my_value")
    try await storage.write(entry: entry)
}
```


## Contributing

### Tests

Gradle tasks to run tests:

`storageTests` - run unit tests for all platforms

`storageTestsAndroid` - run unit tests for Android platform

`storageTestsIos` - run unit tests for iOS platform

Re-running tests when tasks are up-to-date:

`./gradlew :sqlite-storage:storageTests --rerun-tasks`
