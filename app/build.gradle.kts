plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kapt)

    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.androidx.navigation.safeargs.kotlin)
    id(libs.plugins.agconnect.get().pluginId)

}

android {
    namespace = "com.zeynepdogru.cryptoapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.zeynepdogru.cryptoapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    signingConfigs {
        create("config") {

            keyAlias = "yalova2024"
            keyPassword = "yalova2024"
            storeFile = file("project2024.jks")
            storePassword = "yalova2024"
        }
    }

    buildTypes {
        release{
            signingConfig=signingConfigs.getByName("config")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug{
            signingConfig=signingConfigs.getByName("config")
        }
    }




            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
            kotlinOptions {
                jvmTarget = "1.8"
            }
            dataBinding {
                enable = true
            }
    buildFeatures {
        viewBinding = true
    }
        }
        dependencies {

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.appcompat)
            implementation(libs.material)
            implementation(libs.androidx.activity)
            implementation(libs.androidx.constraintlayout)
            testImplementation(libs.junit)
            androidTestImplementation(libs.androidx.junit)
            androidTestImplementation(libs.androidx.espresso.core)

            implementation(libs.retrofit)
            implementation(libs.glide)
            implementation(libs.retrofit.converter.gson)

            implementation(libs.androidx.navigation.fragment.ktx)
            implementation(libs.androidx.navigation.ui.ktx)

            implementation(libs.androidx.room.runtime)
            implementation(libs.androidx.room.ktx)
            kapt(libs.androidx.room.compiler)

            implementation(libs.agconnect.core)
            implementation(libs.hms.push.kit)
            implementation(libs.hms.ads.prime)

        }
