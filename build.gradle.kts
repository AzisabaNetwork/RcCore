plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.shadow)
    alias(libs.plugins.run.paper)
}

group = "net.azisaba.rccore"
version = "0.1.0-SNAPSHOT"
description = "RcCore"

repositories {
    mavenLocal()
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://mvn.lumine.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://repo.azisaba.net/repository/maven-public/")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.maven.apache.org/maven2/")
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://repo.codemc.io/repository/maven-releases/")
    mavenCentral()
}

dependencies {
    compileOnly(libs.paper.api)
    implementation(libs.kotlin.stdlib.jdk8)
    implementation(libs.kaml)

    compileOnly(files("libs/CrazyAuctions-1.7-custom.jar"))

    testImplementation(libs.bundles.kotest)
}

val targetJvmVersion = 21
kotlin {
    jvmToolchain(targetJvmVersion)
    jvmToolchain(21)
}

tasks.build {
    dependsOn(tasks.shadowJar)
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("plugin.yml") {
        expand(props)
    }
}

//tasks.compileJava {
//    options.isFork = true
//    options.encoding = "UTF-8"
//    options.compilerArgs.add("-parameters")
//    options.forkOptions.executable = System.getProperty("java.home") + "/bin/javac"
//}
//
//tasks.compileKotlin {
//    compilerOptions.javaParameters = true
//}
//
//tasks.shadowJar {
//    minimize()
//    relocate("co.aikar.commands", "net.azisaba.rcdamagecore.libs.acf")
//    relocate("co.aikar.locales", "net.azisaba.rcdamagecore.libs.locales")
//}

tasks.runServer {
    minecraftVersion("1.21.1")
}

tasks.test {
    useJUnitPlatform()
}
