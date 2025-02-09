plugins {
    kotlin("jvm") version "1.5.30"
    id("application")
    id("java")
    id("idea")
    id("java-library")
    id("com.github.ben-manes.versions") version "0.39.0"
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(11))

val gradleDependencyVersion = "7.1.1"

tasks.wrapper {
    gradleVersion = gradleDependencyVersion
    distributionType = Wrapper.DistributionType.ALL
}

// Use the MasterApp main class when the JAR is invoked directly
application.mainClass.set("com.awssamples.MasterApp")

tasks.distZip { enabled = false }
tasks.distTar { enabled = false }

// Specify all of our dependency versions
val awsCdkVersion = "1.120.0"
val vavrVersion = "0.10.4"
val slf4jVersion = "2.0.0-alpha1"
val commonsLangVersion = "3.12.0"
val commonsIoVersion = "2.11.0"
val ztZipVersion = "1.14"
val resultsIteratorForAwsJavaSdkVersion = "29.0.14"
val daggerVersion = "2.38.1"
val junitVersion = "4.13.2"
val awsLambdaServletVersion = "0.3.7"
val awsCdkConstructsForJavaVersion = "0.16.0"
val googleGuavaVersion = "30.1.1-jre"
val awsSdk2Version = "2.17.29"
val bouncyCastleVersion = "1.69"
val log4jVersion = "2.14.1"

repositories {
    mavenCentral()
    mavenLocal()
    google()
    maven(url = "https://repo.gradle.org/gradle/libs-releases-local/")
    maven(url = "https://jitpack.io")
}

dependencies {
    // Dagger code generation
    annotationProcessor("com.google.dagger:dagger-compiler:$daggerVersion")

    // Dependency injection with Dagger
    api("com.google.dagger:dagger:$daggerVersion")

    implementation("org.apache.logging.log4j:log4j-api:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    api("org.apache.logging.log4j:log4j-slf4j18-impl:$log4jVersion")

    api("software.amazon.awscdk:core:$awsCdkVersion")
    api("software.amazon.awscdk:iam:$awsCdkVersion")
    api("software.amazon.awscdk:sqs:$awsCdkVersion")
    api("software.amazon.awscdk:iot:$awsCdkVersion")
    api("software.amazon.awscdk:lambda:$awsCdkVersion")
    api("software.amazon.awscdk:dynamodb:$awsCdkVersion")
    api("software.amazon.awscdk:apigateway:$awsCdkVersion")
    implementation("io.vavr:vavr:$vavrVersion")
    api("org.gradle:gradle-tooling-api:$gradleDependencyVersion")
    implementation("org.apache.commons:commons-lang3:$commonsLangVersion")
    implementation("commons-io:commons-io:$commonsIoVersion")
    api("org.zeroturnaround:zt-zip:$ztZipVersion")
    implementation("com.github.awslabs:results-iterator-for-aws-java-sdk:$resultsIteratorForAwsJavaSdkVersion")
    implementation("com.google.guava:guava:$googleGuavaVersion")
    implementation("software.amazon.awssdk:apache-client:$awsSdk2Version")

    api("com.github.aws-samples:aws-lambda-servlet:$awsLambdaServletVersion")

    api("com.github.aws-samples:aws-cdk-constructs-for-java:$awsCdkConstructsForJavaVersion")

    // For certificates
    implementation("org.bouncycastle:bcprov-jdk15on:$bouncyCastleVersion")
    implementation("org.bouncycastle:bcpkix-jdk15on:$bouncyCastleVersion")

    testImplementation("junit:junit:$junitVersion")

    // To force dependabot to update the Gradle wrapper dependency
    testImplementation("org.gradle:gradle-tooling-api:$gradleDependencyVersion")
}
