fun properties(key: String) = project.findProperty(key)?.toString()

plugins {
    kotlin("multiplatform") version "1.8.0"
    `maven-publish`
}

group = "ski.chrzanow"
version = "0.1.0"

repositories {
    mavenCentral()
}

kotlin {
    jvm {
        jvmToolchain(8)
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
//    js(BOTH) {
//        browser {
//            commonWebpackConfig {
//                cssSupport {
//                    enabled.set(true)
//                }
//            }
//        }
//    }
//    val hostOs = System.getProperty("os.name")
//    val isMingwX64 = hostOs.startsWith("Windows")
//    val nativeTarget = when {
//        hostOs == "Mac OS X" -> macosX64("native")
//        hostOs == "Linux" -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
//        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
//    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("org.apache.commons:commons-text:1.10.0")
            }
        }
        val jvmTest by getting
//        val jsMain by getting
//        val jsTest by getting
//        val nativeMain by getting
//        val nativeTest by getting
    }
}

tasks.withType<PublishToMavenLocal>().configureEach {
    dependsOn(tasks.build)
}

tasks.withType<Jar>().configureEach {
    enabled = true
}

publishing {
    publications {
        create<MavenPublication>("transform-string") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()
            from(components["java"])

            pom {
                name.set("transform-string")
                description.set("The transform-string library providing a set of functions for string case transformation.")
                url.set("https://github.com/hsz/transform-string")
                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://github.com/hsz/transform-string/blob/master/LICENSE.md")
                    }
                }
                developers {
                    developer {
                        id.set("hsz")
                        name.set("Jakub Chrzanowski")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/hsz/transform-string.git")
                    developerConnection.set("scm:git:ssh://github.com/hsz/transform-string.git")
                    url.set("https://github.com/hsz/transform-string")
                }
            }
        }
    }
    repositories {
        maven {
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2")

            credentials {
                username = properties("mavenCentralUsername")
                password = properties("mavenCentralPassword")
            }
        }
    }
}
