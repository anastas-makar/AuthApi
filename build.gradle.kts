plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm") version "1.9.0"
    `maven-publish`
}

repositories {
    mavenCentral()  // Указываем Maven Central
    maven { url = uri("https://maven.pkg.github.com/anastas-makar/AuthApi") }
    // jcenter()  // Если необходимо, можно добавить jcenter
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")  // для корутин
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = "pro.progr"
            artifactId = "auth-api"
            version = "0.0.1-alpha"
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/anastas-makar/AuthApi")

            credentials {
                username = (project.findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")).toString()
                password = (project.findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")).toString()
            }
        }
    }
}