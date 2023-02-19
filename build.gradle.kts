import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
}

group = "org.trygrupp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation ("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation ("org.springframework.boot:spring-boot-starter-web:2.6.2")
    implementation ("org.springframework.boot:spring-boot-starter-mail:2.6.2")
    implementation ("io.springfox:springfox-boot-starter:3.0.0")
    implementation ("com.konghq:unirest-java:3.13.4")
    implementation ("org.postgresql:postgresql:42.3.7")
    implementation ("org.apache.commons:commons-lang3:3.4")
    implementation ("org.projectlombok:lombok:1.18.22")
    annotationProcessor ("org.projectlombok:lombok:1.18.22")
    implementation ("org.springframework.boot:spring-boot-devtools:2.6.2")

    implementation ("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly ("io.jsonwebtoken:jjwt-impl:0.11.2")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.2")

    implementation ("org.springframework.boot:spring-boot-starter-security:2.6.2")
    implementation ("org.springframework.boot:spring-boot-starter-data-jpa:2.6.2")
    implementation ("org.springframework.boot:spring-boot-starter-validation:2.6.2")
    implementation ("org.springframework.boot:spring-boot-starter-test:2.6.2")
    implementation ("org.springframework.boot:spring-security-test:2.6.2")

    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}