plugins {
    id("com.android.application") version "8.5.0" apply false
    id("com.android.library") version "8.5.0" apply false
    id("org.jetbrains.kotlin.android") version "2.0.21" apply false
    id("com.vanniktech.maven.publish") version "0.34.0" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}