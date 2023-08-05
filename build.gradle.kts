subprojects {
    setBuildDir("$rootDir/build/${project.path.replace(":", "/")}")
}

project.task("copyJar") {
    copy {
        from("$rootDir/build/application/libs/app.jar")
        into("$rootDir/build/libs")
    }
}

extra["springCloudVersion"] = System.getProperty("springCloudVersion") ?: "2022.0.4"