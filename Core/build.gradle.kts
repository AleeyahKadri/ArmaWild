import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    java
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.codemc.io/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.mikeprimm.com/")
}

dependencies {
    compileOnly("org.spigotmc:spigot-api:1.17-R0.1-SNAPSHOT")
    implementation("io.papermc:paperlib:1.0.6")
    compileOnly("us.dynmap:dynmap-api:3.1")
    compileOnly("org.popcraft:chunky-common:1.2.86")
    compileOnly("org.popcraft:chunkyborder-common:1.0.59")
    compileOnly("org.popcraft:chunkyborder-bukkit:1.0.59")
    compileOnly("com.github.Brettflan:WorldBorder:c0d1772418")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("com.github.TechFortress:GriefPrevention:17.0.0")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.5")
    compileOnly("com.github.WiIIiam278:HuskTowns:1.5.3")
    compileOnly("com.github.angeschossen:LandsAPI:7.15.20")
}

tasks.processResources {
    from(sourceSets.main.get().resources.srcDirs) {
        expand("version" to project.version)
        include("plugin.yml")
    }
}

tasks.named<ShadowJar>("shadowJar") {
    relocate("io.papermc", "biz.donvi.jakesRTP.libs.io.papermc")
}

artifacts {
    add("archives", tasks.named("shadowJar"))
}

tasks.register<Copy>("copyToMainOut") {
    dependsOn(tasks.build)
    from(file("build/libs/Core-${project.version}-all.jar"))
    into(file("../build-output-final"))
    rename("Core-", "JakesRTP-v")
    rename("-all", "")
}

tasks.register<Copy>("prepareTestServer") {
    dependsOn(tasks.named("copyToMainOut"))
    delete(file("../testServer/plugins/JakesRTP.jar"))
    from(file("../build-output-final/JakesRTP-v${project.version}.jar"))
    into(file("../testServer/plugins/"))
    rename("-.*\\.", ".")
}
