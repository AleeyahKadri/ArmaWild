import org.gradle.api.plugins.JavaPluginExtension

defaultTasks("build")

subprojects {
    apply(plugin = "java-library")

    group = "biz.donvi"
    version = "0.14.9"

    extensions.configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
    }

    repositories {
        mavenLocal()
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    tasks.withType<Copy>().configureEach {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}
