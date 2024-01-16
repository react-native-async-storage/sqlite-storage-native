# Async Storage SQLite

Multiplatform SQLite storage for React Native Async Storage

## Usage

```groovy
implementation("io.github.react-native-async-storage:async-storage-sqlite:VERSION")
```

## Testing

Gradle tasks to run tests:

`storageTests` - run unit tests for all platforms

`storageTestsAndroid` - run unit tests for Android platform

`storageTestsIos` - run unit tests for iOS platform

Re-running tests when tasks are up-to-date:

`./gradlew :sqlite-storage:storageTests --rerun-tasks`

## Release

1. Update version in `package_info.json`
2. Build binaries `bundleSQLiteStorage`
3. Commit and tag 
4. Push to repo
