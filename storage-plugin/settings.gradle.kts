dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            from(files(File(rootDir, "../gradle/libs.versions.toml")))
        }
    }
}
