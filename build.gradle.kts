buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

group = "hmtsoi"
description = "java timezone app"

plugins {
    java
    `jvm-test-suite`
    id("org.springframework.boot") version "4.0.1"
    id("io.spring.dependency-management") version "1.1.7"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

repositories {
    mavenCentral()
}

dependencies {
    // spring-boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // jackson-dataformat-xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.20.1")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // postgresql
    implementation("org.postgresql:postgresql:42.7.8")
    implementation("org.hibernate.orm:hibernate-core:7.1.13.Final")
    implementation("org.hibernate.orm:hibernate-spatial:7.1.13.Final")

    // google cloud -- comment out to connect local database
    implementation("com.google.cloud:spring-cloud-gcp-starter-sql-postgresql:7.4.2")
    implementation("com.google.cloud:spring-cloud-gcp-dependencies:7.4.2")

    // settings
    implementation("commons-configuration:commons-configuration:1.10")

    // testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation(project(mapOf("path" to ":")))
}

tasks.named<Jar>("jar") {
    enabled = true
}

tasks.named<Jar>("bootJar") {
    enabled = true
    archiveFileName.set("app.jar")
}

testing {
    suites {
        withType<JvmTestSuite> {
            useJUnitJupiter()
        }
        val integrationTest by registering(JvmTestSuite::class) {
            sources {
                java {
                    setSrcDirs(listOf("src/main/java", "src/integration-test/java"))
                }
            }
            dependencies {
                project.dependencies
                compileOnly("org.projectlombok:lombok")
                annotationProcessor("org.projectlombok:lombok")
                implementation("io.rest-assured:rest-assured:6.0.0")
                implementation("io.rest-assured:rest-assured-all:6.0.0")
                implementation("io.rest-assured:spring-mock-mvc:6.0.0")
                implementation("io.rest-assured:spring-web-test-client:6.0.0")
                implementation("io.rest-assured:json-path:6.0.0")
                implementation("org.junit.jupiter:junit-jupiter-api:6.0.1")
                implementation("org.junit.jupiter:junit-jupiter-engine:6.0.1")
                implementation("org.mockito:mockito-junit-jupiter:5.21.0")
            }
        }
    }
}

val integrationTestImplementation by configurations.getting {
    extendsFrom(configurations.testImplementation.get())
}

tasks.named("check") {
    dependsOn(testing.suites.named("integrationTest"))
}
