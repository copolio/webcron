subprojects {
    val moduleBuildPath = project.displayName.substring(9).dropLast(1).replace(":", "/")
    buildDir = File("$rootDir/build$moduleBuildPath")
}

task("copyJar") {
    copy({
        from("$buildDir/application/libs/app.jar")
        into("$buildDir")
    })
}