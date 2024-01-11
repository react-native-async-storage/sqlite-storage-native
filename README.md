# Async Storage Sqlite

Multiplatform sqlite storage for React Native AsyncStorage

## Testing

Gradle tasks to run tests:

`storageTests` - run unit tests for all platforms

`storageTestsAndroid` - run unit tests for Android platform

`storageTestsIos` - run unit tests for iOS platform

Re-running tests when tasks are up-to-date:

`./gradlew :sqlite-storage:storageTests --rerun-tasks`

## Release

- Update version in `package.json` file, via `yarn version --patch|minor|major --no-git-tag-version`
- commit and push version bump to repo
