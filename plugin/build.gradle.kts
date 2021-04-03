import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    `maven-publish`
    id("tech.poder.simpleAPI") version "1.0.0"
}

group = "tech.poder"
version = "1.0.0"

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
    implementation("com.guardsquare:proguard-core:7.1.0-beta3")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions.useIR = true
        sourceCompatibility = JavaVersion.VERSION_16.toString()
        targetCompatibility = JavaVersion.VERSION_16.toString()
        kotlinOptions.jvmTarget = JavaVersion.VERSION_15.toString()
        kotlinOptions.languageVersion = "1.4"
        kotlinOptions.apiVersion = "1.4"
    }

    withType<JavaCompile> {
        sourceCompatibility = JavaVersion.VERSION_16.toString()
        targetCompatibility = JavaVersion.VERSION_16.toString()
    }

}

gradlePlugin {
    plugins {
        create("pluginAPI") {
            id = "tech.poder.simpleAPI"
            implementationClass = "tech.poder.simpleAPI.APIPlugin"
        }
    }
}