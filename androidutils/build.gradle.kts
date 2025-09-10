plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
}

android {
    namespace = "org.matools.androidutils"
    compileSdk = 36

    defaultConfig {
        minSdk = 21
        targetSdk = 36

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                
                groupId = "org.matools"
                artifactId = "androidutils"
                version = "0.0.1"
                
                pom {
                    name.set("Android Utils")
                    description.set("A comprehensive Android utility library")
                    url.set("https://github.com/Mathrusoft/android-utils")
                    
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    
                    developers {
                        developer {
                            id.set("mathrusoft")
                            name.set("Mathrusoft")
                            email.set("opensource@mathrusoft.com")
                        }
                    }
                    
                    scm {
                        connection.set("scm:git:git://github.com/Mathrusoft/android-utils.git")
                        developerConnection.set("scm:git:ssh://github.com/Mathrusoft/android-utils.git")
                        url.set("https://github.com/Mathrusoft/android-utils")
                    }
                }
            }
        }
        
        repositories {
            // Maven Central
            maven {
                name = "OSSRH"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = findProperty("NEXUS_USERNAME")?.toString()
                    password = findProperty("NEXUS_PASSWORD")?.toString()
                }
            }
            
            // GitHub Packages
            maven {
                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/Mathrusoft/android-utils")
                credentials {
                    username = findProperty("GITHUB_USERNAME")?.toString()
                    password = findProperty("GITHUB_TOKEN")?.toString()
                }
            }
        }
    }
    
    signing {
        sign(publishing.publications["release"])
    }
}
