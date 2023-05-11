@file:Suppress("DSL_SCOPE_VIOLATION")
plugins {
    id("java-library")
    id("kotlin")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

// 아무 모듈에도 의존하지 않고 외부 라이브러리만 사용
dependencies{
    // 도메인에서 필요한 라이브러리
    // 예시에서는 kotlin.stdlib, javax.inject,
    // coroutine.core, paging.common 이 있음
    implementation(libs.coroutine.core)
    // domain에서는 안드로이드 종속적인 라이브러리를 사용할 수 없기 때문에
    // 의존성 주입 위해 다음 값 사용
    implementation("javax.inject:javax.inject:1")
}