buildscript {
    repositories {
        maven("https://plugins.gradle.org/m2/")
    }
}

plugins {
    java
    `jvm-test-suite`
    id("org.springframework.boot") version "3.0.1"
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
    implementation("org.springframework.boot:spring-boot-starter-jersey")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-devtools")

    // jackson-dataformat-xml
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.4.4")

    // lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    // postgresql
    implementation("org.postgresql:postgresql:42.5.1")
    implementation("org.hibernate.orm:hibernate-core:6.1.6.Final")
    implementation("org.hibernate.orm:hibernate-spatial:6.1.6.Final")

    // settings
    implementation("commons-configuration:commons-configuration:1.10")

    // testing
    testImplementation("org.springframework.boot:spring-boot-starter-test")

}

tasks.named<Jar>("jar") {
    enabled = false
}

tasks.named<Jar>("bootJar") {
    enabled = false
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter()
        }
        val integrationTest by registering(JvmTestSuite::class) {
            testType.set(TestSuiteType.INTEGRATION_TEST)
            sources {
                java {
                    setSrcDirs(listOf("src/main/java", "src/integration-test/java"))
                }
            }
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
