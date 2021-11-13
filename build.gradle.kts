buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(BuildDependencies.buildGradle)
        classpath(BuildDependencies.kotlinGradlePlugin)
        classpath(SupportDependencies.hiltDaggerAndroidPlugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}