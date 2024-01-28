import com.github.gradle.node.npm.task.NpmTask

plugins {
    id("com.github.node-gradle.node") version("5.0.0")
    id("java")
}

group = "com.copolio"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17


node {
   version.set("16.16.0")
    npmVersion.set("8.11.0")
    download.set(true)
}

tasks.register<NpmTask>("npmRunBuild") {
    dependsOn(tasks.npmInstall)
    npmCommand.set(listOf("run", "build"))
}
