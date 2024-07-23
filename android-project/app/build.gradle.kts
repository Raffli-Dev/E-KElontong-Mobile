plugins {
    id("com.android.application")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile =
                file("/home/rorik/DATA/ANDROID/PROJECT/keystore_android/eklontong_unmer/eklontong_unmer_fix.jks")
            storePassword = "eklontong_unmer"
            keyAlias = "eklontong_unmer"
            keyPassword = "eklontong_unmer"
        }
    }
    namespace = "id.eklontong_umkm"
    compileSdk = 34

    defaultConfig {
        applicationId = "id.eklontong_umkm"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")

    implementation("androidx.multidex:multidex:2.0.1")
    implementation("androidx.core:core:1.10.1")
//    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.vectordrawable:vectordrawable:1.1.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
//    implementation("com.google.android:flexbox:0.3.2")
    implementation("androidx.browser:browser:1.5.0")

    //ad network
//    implementation("com.google.android.gms:play-services-ads:22.2.0")
//    implementation("com.google.android.ads.consent:consent-library:1.0.8")
//    implementation("com.facebook.android:audience-network-sdk:6.14.0")
//    implementation("com.unity3d.ads:unity-ads:4.7.1")
//    implementation("com.applovin:applovin-sdk:11.10.1")
//    implementation("com.ironsource.sdk:mediationsdk:7.3.1")

    //ad network mediation
//    implementation("com.google.ads.mediation:facebook:6.14.0.0")
//    implementation("com.applovin.mediation:google-adapter:21.4.0.1")
//    implementation("com.applovin.mediation:facebook-adapter:6.14.0.0")
//    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")


//    implementation("com.github.chrisbanes:PhotoView:2.3.0")

    // third party dependencies -------------------------------------------------------------------
    implementation("com.balysv:material-ripple:1.0.2")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.3")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.2")

    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")

    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

//    def lifecycle_version = "2.2.0"
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-runtime:2.2.0")
    annotationProcessor("androidx.lifecycle:lifecycle-compiler:2.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}