plugins {
    alias(libs.plugins.pardess.android.library)
    alias(libs.plugins.pardess.android.room)
    alias(libs.plugins.pardess.hilt)
}

android {
    namespace = "com.pardess.database"
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}