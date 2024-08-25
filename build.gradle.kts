plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.23"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.9.23"
    id("com.google.devtools.ksp") version "1.9.23-1.0.19"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.2"
    id("io.micronaut.aot") version "4.4.2"
}

version = "0.1"
group = "dev.georgiys"

val kotlinVersion = project.properties["kotlinVersion"]

repositories {
    mavenCentral()
}

dependencies {
    ksp("io.micronaut:micronaut-http-validation")
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    implementation("io.micronaut:micronaut-validation:3.10.4")
    implementation("jakarta.validation:jakarta.validation-api:3.1.0")
    implementation("org.hibernate.validator:hibernate-validator:8.0.1.Final")
    implementation("io.micronaut.data:micronaut-data-processor:4.9.0")
    implementation("org.hibernate.orm:hibernate-core:6.6.0.Final")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari:5.8.1")
    implementation("io.micronaut.data:micronaut-data-hibernate-jpa:4.9.0")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("io.micronaut:micronaut-aop:4.6.3")
    implementation("io.micronaut:micronaut-jdbc-hikari")
    compileOnly("io.micronaut:micronaut-http-client")
    runtimeOnly("org.yaml:snakeyaml")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("io.micronaut.testresources:micronaut-test-resources-jdbc-postgresql:2.6.0")
    testImplementation("io.micronaut:micronaut-http-client")
    testImplementation("com.h2database:h2:2.3.232")
}

application {
    mainClass.set("dev.georgiys.ApplicationKt")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks.named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
    archiveClassifier.set("") // Avoid "-all" classifier
    manifest {
        attributes("Main-Class" to "dev.georgiys.ApplicationKt")
    }
}

tasks {
    build {
        dependsOn("shadowJar")
    }
}

graalvmNative.toolchainDetection = false

micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("dev.georgiys.*")
    }
    aot {
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}

tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "17"
}
tasks.named("mergeServiceFilesForOptimizedNativeJar") {
    dependsOn("shadowJar")
}

tasks.named("prepareNativeOptimizations") {
    dependsOn("shadowJar")
}

tasks.named("mergeServiceFilesForOptimizedJitJar") {
    dependsOn("shadowJar")
}

tasks.named("prepareJitOptimizations") {
    dependsOn("shadowJar")
}

tasks.named("distTar") {
    dependsOn("shadowJar")
}

tasks.named("startScripts") {
    dependsOn("shadowJar")
}

tasks.named("distZip") {
    dependsOn("shadowJar")
}
tasks.named("startShadowScripts") {
    dependsOn("jar")
}