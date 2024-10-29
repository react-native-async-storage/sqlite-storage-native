## Release

### Before release

1. Run lint check via gradle task `spotlessCheck`
2. Run tests via gradle task `storageTests`
3. Run `pod lib lint` to verify Podspec

### Versioning

1. Update version in [package_info.json](package_info.json)
2. Build binaries via `bundleSQLiteStorage` gradle task
3. Update CHANGELOG
4. Commit changes (name it `release: MAJOR.MINOR.PATCH`)
5. Create tag (name it `MAJOR.MINOR.PATCH`)
6. Push commit and tag to `main`
7. Create Github release

### Android

https://vanniktech.github.io/gradle-maven-publish-plugin/central/#publishing-releases

### iOS

https://guides.cocoapods.org/making/making-a-cocoapod.html#submitting-open-source-code
