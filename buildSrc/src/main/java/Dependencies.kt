import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.Dependency

object Version{
    const val roomVersion = "2.5.2"
    const val viewModelKTXVersion = "2.6.1"
    const val coroutinesVersion = "1.7.2"
    const val navVersion = "2.7.1"
    const val daggerVersion = "2.47"
    const val timberVersion = "5.0.1"
    const val coreKTXVersion = "1.10.1"
    const val lifecycleRuntimeKTXVersion = "2.6.1"
    const val activityComposeVersion = "1.8.0-alpha07"
    const val composeBomVersion = "2023.03.00"
    const val jUnitVersion = "4.13.2"
    const val androidJUnitVersion = "1.1.5"
    const val espressoVersion = "3.5.1"
    const val hiltNavVersion = "1.0.0"
    const val timePickerVersion = "1.1.11"
    const val desugaringVersion = "2.0.3"
}

object Dependencies{
    const val timePicker = "com.github.commandiron:WheelPickerCompose:${Version.timePickerVersion}"
    const val desugaring = "com.android.tools:desugar_jdk_libs:${Version.desugaringVersion}"
    const val room = "androidx.room:room-runtime:${Version.roomVersion}"
    const val roomKsp = "androidx.room:room-compiler:${Version.roomVersion}"
    const val roomKTX = "androidx.room:room-ktx:${Version.roomVersion}"
    const val coreKTX = "androidx.core:core-ktx:${Version.coreKTXVersion}"
    const val viewModelKTX = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.viewModelKTXVersion}"
    const val lifecycleKTX = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycleRuntimeKTXVersion}"
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesVersion}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesVersion}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Version.navVersion}"
    const val hilt = "com.google.dagger:hilt-android:${Version.daggerVersion}"
    const val hiltKapt = "com.google.dagger:hilt-android-compiler:${Version.daggerVersion}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Version.hiltNavVersion}"
    const val timber = "com.jakewharton.timber:timber:${Version.timberVersion}"
    const val activity = "androidx.activity:activity-compose:${Version.activityComposeVersion}"
    const val composeBom = "androidx.compose:compose-bom:${Version.composeBomVersion}"
    const val composeUi = "androidx.compose.ui:ui"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val composeMaterial = "androidx.compose.material3:material3"
    const val composeJUnit = "androidx.compose.ui:ui-test-junit4"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiTestManifest = "androidx.compose.ui:ui-test-manifest"
    const val jUnit = "junit:junit:${Version.jUnitVersion}"
    const val testJUnit = "androidx.test.ext:junit:${Version.androidJUnitVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espressoVersion}"
}

private fun DependencyHandler.implementation(depName: Dependency) {
    add("implementation", depName)
}
private fun DependencyHandler.implementation(depName: String) {
    add("implementation", depName)
}
private fun DependencyHandler.testImplementation(depName: String) {
    add("testImplementation", depName)
}
private fun DependencyHandler.androidTestImplementation(depName: String) {
    add("androidTestImplementation", depName)
}
private fun DependencyHandler.androidTestImplementation(depName: Dependency) {
    add("androidTestImplementation", depName)
}
private fun DependencyHandler.debugImplementation(depName: String) {
    add("debugImplementation", depName)
}
private fun DependencyHandler.kapt(depName: String) {
    add("kapt", depName)
}

private fun DependencyHandler.ksp(depName: String) {
    add("ksp", depName)
}
private fun DependencyHandler.compileOnly(depName: String) {
    add("compileOnly", depName)
}
private fun DependencyHandler.api(depName: String) {
    add("api", depName)
}
private fun DependencyHandler.desugaring(depName: String) {
    add("coreLibraryDesugaring", depName)
}


fun DependencyHandler.timePicker() {
    implementation(Dependencies.timePicker)
}

fun DependencyHandler.desugaring() {
    desugaring(Dependencies.desugaring)
}

fun DependencyHandler.room() {
    implementation(Dependencies.room)
    implementation(Dependencies.roomKTX)
    ksp(Dependencies.roomKsp)
}

fun DependencyHandler.core() {
    implementation(Dependencies.coreKTX)
}

fun DependencyHandler.viewModel() {
    implementation(Dependencies.viewModelKTX)
}

fun DependencyHandler.lifecycle() {
    implementation(Dependencies.lifecycleKTX)
}

fun DependencyHandler.coroutines() {
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.composeNavigation)
    implementation(Dependencies.hiltNavigation)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hilt)
    kapt(Dependencies.hiltKapt)
}

fun DependencyHandler.timber() {
    implementation(Dependencies.timber)
}

fun DependencyHandler.activity() {
    implementation(Dependencies.activity)
}

fun DependencyHandler.compose() {
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiGraphics)
    implementation(Dependencies.composeUiToolingPreview)
    implementation(Dependencies.composeMaterial)
    androidTestImplementation(platform(Dependencies.composeBom))
    androidTestImplementation(Dependencies.composeJUnit)
    debugImplementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiTestManifest)
}

fun DependencyHandler.test() {
    testImplementation(Dependencies.jUnit)
    androidTestImplementation(Dependencies.testJUnit)
    androidTestImplementation(Dependencies.espresso)
}


