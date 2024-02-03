import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val springBootVersion = System.getProperty("springBootVersion")!!
	val springDependencyManagementVersion = System.getProperty("springDependencyManagementVersion")!!
	val kotlinJvmVersion = System.getProperty("kotlinJvmVersion")!!

	id("org.springframework.boot") version springBootVersion
	id("io.spring.dependency-management") version springDependencyManagementVersion
	kotlin("jvm") version kotlinJvmVersion
	kotlin("plugin.spring") version kotlinJvmVersion
	kotlin("plugin.jpa") version kotlinJvmVersion
	kotlin("kapt") version kotlinJvmVersion
}

group = "com.copolio.domains"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	kapt("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// Database
	api("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("com.mysql:mysql-connector-j")

	// Multi-module
	implementation(project(":core:spring-yaml-importer"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootJar { enabled = false }