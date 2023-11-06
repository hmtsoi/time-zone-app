buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

plugins {
    java
    `jvm-test-suite`
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.0"
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // spring-boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // jackson-dataformat-xml
    implementation("com.fasterxml.jackson.module:jackson-module-jakarta-xmlbind-annotations:2.15.3")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // postgresql
    implementation("org.postgresql:postgresql:42.6.0")
    implementation("org.hibernate.orm:hibernate-core:6.2.13.Final")
    implementation("org.hibernate.orm:hibernate-spatial:6.2.13.Final")

    // google cloud -- comment out to connect local database
    implementation("com.google.cloud:spring-cloud-gcp-starter-sql-postgresql:4.8.1")
    implementation("com.google.cloud:spring-cloud-gcp-dependencies:4.8.1")

    // settings
    implementation("commons-configuration:commons-configuration:1.10")

    // testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
        val integrationTest by registering(JvmTestSuite::class) {
            sources {
                java {
                    setSrcDirs(listOf("src/main/java", "src/integration-test/java"))
                }
            }
            testType.set(TestSuiteType.INTEGRATION_TEST)
            dependencies {
                project.dependencies
                compileOnly("org.projectlombok:lombok")
                annotationProcessor("org.projectlombok:lombok")
                implementation("io.rest-assured:rest-assured:5.3.0")
                implementation("io.rest-assured:rest-assured-all:5.3.0")
                implementation("io.rest-assured:spring-mock-mvc:5.3.0")
                implementation("io.rest-assured:spring-web-test-client:5.3.0")
                implementation("io.rest-assured:json-path:5.3.0")
                implementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
                implementation("org.junit.jupiter:junit-jupiter-engine:5.6.2")
                implementation("org.mockito:mockito-junit-jupiter:3.3.3")
            }
            targets {
                all {
                    testTask.configure {
                        shouldRunAfter(test)
                    }
                }
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
