plugins {
    kotlin("jvm") version "1.5.30"
    id("application")
    id("java")
    id("idea")

    // Creates fat JAR
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

extensions.findByName("buildScan")?.withGroovyBuilder {
    setProperty("termsOfServiceUrl", "https://gradle.com/terms-of-service")
    setProperty("termsOfServiceAgree", "yes")
}

idea.module.isDownloadSources = true
idea.module.isDownloadJavadoc = true

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

val gradleDependencyVersion = "7.1.1"

tasks.wrapper {
    gradleVersion = gradleDependencyVersion
    distributionType = Wrapper.DistributionType.ALL
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
    maven(url = "https://repo.gradle.org/gradle/libs-releases-local/")
}

application.mainClassName = "not-applicable"

tasks.distZip { enabled = false }
tasks.distTar { enabled = false }
tasks.shadowDistZip { enabled = false }
tasks.shadowDistTar { enabled = false }

tasks.shadowJar {
    // AWS Lambda specific - fixes "StatusLogger Unrecognized format specifier [d]" errors, from https://stackoverflow.com/questions/48033792/log4j2-error-statuslogger-unrecognized-conversion-specifier
    transform(com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer::class.java)
}

// Specify all of our dependency versions
val slf4jVersion = "1.7.32"
val gsonVersion = "2.8.8"
val cborVersion = "4.4.4"
val awsSdk2Version = "2.17.29"
val junitVersion = "4.13.2"
val awsLambdaJavaCoreVersion = "1.2.1"
val vavrVersion = "0.10.4"

dependencies {
    implementation("io.vavr:vavr:$vavrVersion")

    implementation( "com.amazonaws:aws-lambda-java-core:$awsLambdaJavaCoreVersion")
    implementation( "software.amazon.awssdk:iot:$awsSdk2Version")
    implementation( "software.amazon.awssdk:iotdataplane:$awsSdk2Version")

    implementation( "com.google.code.gson:gson:$gsonVersion")
    implementation( "com.upokecenter:cbor:$cborVersion")

    implementation( "org.slf4j:slf4j-log4j12:$slf4jVersion")

    testImplementation("junit:junit:$junitVersion")
}
