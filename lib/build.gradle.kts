plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.10"

    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            useKotlinTest("1.8.10")
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}
