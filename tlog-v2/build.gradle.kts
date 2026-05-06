plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.vanniktech.maven.publish")
}

android {
    namespace = "com.tao.tlog.v2"
    compileSdk = 34

    defaultConfig {
        minSdk = 16

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}


mavenPublishing {
    publishToMavenCentral()
    signAllPublications()
    coordinates(
        "io.github.wangjintao",
        "tlog",
        "2.0.0"
    )

    pom {
        name.set("TLog")
        description.set("Lightweight Android logging library")
        url.set("https://github.com/wangjintao/TLog")

        licenses {
            license {
                name.set("Apache-2.0")
                url.set("https://opensource.org/licenses/Apache-2.0")
            }
        }

        developers {
            developer {
                id.set("wangjintao")
                name.set("WangJintao")
                email.set("wangjintao1988@163.com")
            }
        }

        scm {
            connection.set("scm:git:github.com/wangjintao/TLog.git")
            developerConnection.set("scm:git:ssh://github.com:wangjintao/TLog.git")
            url.set("https://github.com/wangjintao/TLog")
        }
    }
}


dependencies {

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test:runner:1.7.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
}