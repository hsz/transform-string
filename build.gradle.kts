fun properties(key: String) = project.findProperty(key)?.toString()

plugins {
    `maven-publish`
    signing
    kotlin("jvm") version "1.8.0"
}

group = "ski.chrzanow"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.commons:commons-text:1.10.0")
    testImplementation(kotlin("test"))
}

java {
    withSourcesJar()
    withJavadocJar()
}

tasks {
    withType<PublishToMavenLocal>().configureEach {
        dependsOn(build)
    }
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
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2")

            credentials {
                username = properties("mavenCentralUsername")
                password = properties("mavenCentralPassword")
            }
        }
    }
}

signing {
    val signingKey = properties("signingKey")
    val signingPassword = properties("signingPassword")

    isRequired = !signingKey.isNullOrEmpty() && !signingPassword.isNullOrEmpty()

    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications["transform-string"])
}
