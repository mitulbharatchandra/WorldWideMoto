package com.worldwidemoto.di
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.ksp.generated.startKoin

@Configuration
@Module(includes = [])
class AppModule

@KoinApplication
object KoinApp

fun initKoin(configuration : KoinAppDeclaration? = null) {
    KoinApp.startKoin {
        includes(configuration)
    }
}