import java.util.Properties

plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id ("org.flywaydb.flyway") version "10.11.0"
}



buildscript {
    dependencies {
        classpath("org.postgresql:postgresql:42.7.1")
        classpath("org.flywaydb:flyway-database-postgresql:10.11.0")
    }
}
group = "id.ac.ui.cs.advprog"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}


flyway {
    url = System.getenv("DATABASE_URL") ?: "jdbc:postgresql://ep-crimson-unit-a5tia4mt.us-east-2.aws.neon.tech/TestDatabase?user=neondb_owner&password=2NAZcQODYdX6&sslmode=require"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.flywaydb:flyway-database-postgresql:10.11.0")
    implementation ("org.flywaydb:flyway-core:10.11.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
