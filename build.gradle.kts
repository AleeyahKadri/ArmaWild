defaultTasks("build")

subprojects {
    apply(plugin = "java-library")

    group = "biz.donvi"
    version = "0.14.9"

    configure<JavaPluginExtension> {
        sourceCompatibility = JavaVersion.VERSION_16
        targetCompatibility = JavaVersion.VERSION_16
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    repositories {
        mavenLocal()
        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        }
    }

    tasks.withType<Copy> {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}
