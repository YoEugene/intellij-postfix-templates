plugins {
    // Java support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    // Gradle Changelog Plugin
    //id("org.jetbrains.changelog") version "1.3.1"
    // Gradle Qodana Plugin
    id("org.jetbrains.qodana") version "0.1.13"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.9.0"
}

group = "com.intellij"
version = "2.15.0.223"

tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
    options.encoding = "UTF-8"
}

repositories {
    mavenCentral()
}


dependencies {
    implementation("commons-io:commons-io:2.11.0")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.0")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.14.0")
    implementation("io.sentry:sentry:6.7.0") {
        exclude("org.slf4j")
    }
    // https://mvnrepository.com/artifact/org.projectlombok/lombok

    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    testCompileOnly("org.projectlombok:lombok:1.18.24")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.24")

    testImplementation("junit:junit:4.13.2")
}




sourceSets {
    main {
        java.srcDirs("src", "gen")
        resources.srcDir("resources")
    }

    test {
        java.srcDir("test/src")
        resources.srcDir("test/resources")
    }

}


intellij {
    // full list of IntelliJ IDEA releases at https://www.jetbrains.com/intellij-repository/releases
    // full list of IntelliJ IDEA EAP releases at https://www.jetbrains.com/intellij-repository/snapshots
    //version "IU-212.4037-EAP-CANDIDATE-SNAPSHOT"
    version.set("IU-223.7571-EAP-CANDIDATE-SNAPSHOT")
    //version "IU-222.3345.118"
    plugins.set(
        listOf(
            "java",
            //"PythonCore:201.6073.9",
            "Pythonid:223.7571.4",
            "Kotlin",
            "org.intellij.scala:2022.3.10",
            "JavaScript",
            //"CSS",
            "Dart:223.7571.10",
            "Groovy",
            "properties",
            "org.jetbrains.plugins.ruby:223.7571.4",
            "com.jetbrains.php:223.7571.4",
            "java-i18n",
            "DatabaseTools",
            "org.rust.lang:0.4.182.4996-223",
            "org.toml.lang",
            "org.jetbrains.plugins.go:223.7571.4",
            "nl.rubensten.texifyidea:0.7.24"
        )
    )
    updateSinceUntilBuild.set(true)
}

tasks {
    // Avoid ClassNotFoundException: com.maddyhome.idea.copyright.psi.UpdateCopyrightsProvider
    buildSearchableOptions {
        // jvmArgs = ["-Djava.system.class.loader=com.intellij.util.lang.PathClassLoader"]
        enabled = false
    }

    runPluginVerifier {
        ideVersions.set(listOf(intellij.version.get()))
        //ideVersions("IU-222.3345.118")
        //setFailureLevel(RunPluginVerifierTask.FailureLevel.ALL)
    }

    publishPlugin {
        token.set(System.getenv("ORG_GRADLE_PROJECT_intellijPublishToken"))
    }
}
