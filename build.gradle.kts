plugins {
	alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.ktlint.gradle) apply false
}

subprojects {
	apply(plugin = "org.jlleitschuh.gradle.ktlint") // Can't use alias here, use concrete name

	configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
		android.set(true)
		outputToConsole.set(true)
	}
}
