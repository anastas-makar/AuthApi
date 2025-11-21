// build.gradle.kts (JVM lib)
plugins {
    id("java-library")
    kotlin("jvm") version "1.9.10"
    id("maven-publish")
}

repositories {
    mavenCentral() // ЭТОГО достаточно для корутин и прочего
    // НИКАКИХ GitHub Packages здесь
}

java {
    toolchain { languageVersion.set(JavaLanguageVersion.of(17)) }
}
tasks.withType<JavaCompile>().configureEach {
    options.release.set(8) // таргетим байткод под Java 8
}
kotlin {
    jvmToolchain(17)
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")

    implementation(platform("com.squareup.okhttp3:okhttp-bom:4.12.0"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")
    testImplementation("junit:junit:4.13.2")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = "pro.progr"
            artifactId = "auth-api"
            version = "0.0.3-alpha"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/anastas-makar/AuthApi")
            credentials {
                username = (findProperty("gpr.user") ?: System.getenv("GITHUB_USERNAME")).toString()
                password = (findProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")).toString()
            }
        }
    }
}
