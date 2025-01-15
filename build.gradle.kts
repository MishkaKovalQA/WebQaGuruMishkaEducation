plugins {
    id("java")
    id("io.freefair.lombok") version "8.11"
}

group = "qa.guru.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("com.codeborne:selenide:7.6.1")

    testImplementation("org.junit.jupiter:junit-jupiter:5.11.4")

    testImplementation("ch.qos.logback:logback-classic:1.5.16")

    testImplementation("org.assertj:assertj-core:3.27.2")

    implementation("com.google.code.gson:gson:2.11.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.18.2")
    implementation("com.codeborne:xls-test:1.4.3")
    implementation("com.codeborne:pdf-test:1.5.0")
    implementation("com.opencsv:opencsv:5.9")

    implementation("com.github.javafaker:javafaker:1.0.2")
}

tasks.test {
    useJUnitPlatform()
    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.mode.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.mode.classes.default", "concurrent")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "fixed")
    systemProperty("junit.jupiter.execution.parallel.config.fixed.parallelism", "5")

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}