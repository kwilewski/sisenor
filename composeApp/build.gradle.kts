import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.sqlDelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }
    
    jvm("desktop")
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.materialIconsExtended)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)

                implementation(libs.runtime)
                implementation(libs.coroutines.extensions)
                implementation(libs.kotlinx.datetime)
                implementation(libs.mvvm.core)
                implementation(libs.mvvm.compose)
                implementation(libs.mvvm.flow)
                implementation(libs.mvvm.flow.compose)


                implementation(libs.voyager.navigator)
                implementation(libs.voyager.screenmodel)
                implementation(libs.voyager.bottom.sheet.navigator)
                implementation(libs.voyager.tab.navigator)
                implementation(libs.voyager.tab.transitions)

                implementation(libs.window.size)

                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.lifecycle.viewmodel)
                implementation(libs.navigation.compose)

                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")


            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(libs.sqldelight.desktop)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation ("app.cash.sqldelight:native-driver:2.0.1")
                implementation(libs.sqldelight.ios)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }



        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
            implementation ("app.cash.sqldelight:android-driver:2.0.1")
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.sqldelight.android)
        }
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }

    }


}

android {
    namespace = "com.narrowstudio"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.narrowstudio"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

sqldelight{
    databases {
        create("WordDatabase") {
            packageName.set("com.narrowstudio.sisenor.database")
            srcDirs.setFrom("src/commonMain/sqldelight")
        }
    }

}

dependencies {
    implementation("androidx.core:core:1.12.0")
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.room.common)
    commonMainApi("dev.icerock.moko:mvvm-core:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-compose:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow:0.16.1")
    commonMainApi("dev.icerock.moko:mvvm-flow-compose:0.16.1")
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.narrowstudio"
            packageVersion = "1.0.0"
        }
    }
}



