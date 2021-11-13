plugins {
    id(BuildDependencies.androidApplication)
    id(BuildDependencies.kotlinAndroid)
    id(BuildDependencies.kotlinKapt)
    id(BuildDependencies.kotlinParcelize)
    id(BuildDependencies.daggerHiltPlugin)
}

android {
    compileSdk = Versions.compileSdkVersion
    defaultConfig {
        applicationId = BuildDependencies.applicationId
        minSdk = Versions.minSdkVersion
        targetSdk = Versions.targetSdkVersion
        buildToolsVersion = Versions.buildToolsVersion

        versionCode = Versions.versionCode
        versionName = Versions.versionName

        testInstrumentationRunner = TestingDependencies.jUnitRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    buildFeatures {
        dataBinding = true
    }


}

dependencies {
    implementation(BuildDependencies.coreKtx)

    implementation(LayoutDependencies.appCompat)
    implementation(LayoutDependencies.constraintLayout)
    implementation(LayoutDependencies.materialComponents)

    implementation(SupportDependencies.room)
    implementation(SupportDependencies.roomKtx)
    implementation(SupportDependencies.activityKtx)
    implementation(SupportDependencies.lifecycleExtensions)
    implementation(SupportDependencies.liveDataExtensions)
    implementation(SupportDependencies.viewModelLifeCycle)
    implementation(SupportDependencies.daggerHilt)
    implementation(SupportDependencies.hiltLifecycleViewModel)
    implementation(SupportDependencies.glide)
    implementation(SupportDependencies.volley)

    kapt(SupportDependencies.roomCompiler)
    kapt(SupportDependencies.hiltAndroidCompiler)
    kapt(SupportDependencies.hiltCompiler)
    kapt(SupportDependencies.glideProcessor)

    testImplementation(TestingDependencies.jUnit)
    testImplementation(TestingDependencies.coroutineTst)
    testImplementation(TestingDependencies.coreTesting)
    testImplementation(TestingDependencies.mockito)
    testImplementation(TestingDependencies.androidxCoreTesting)

}