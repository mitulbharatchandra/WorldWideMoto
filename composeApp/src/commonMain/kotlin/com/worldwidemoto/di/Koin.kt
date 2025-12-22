package com.worldwidemoto.di
import com.firebase.core.di.FirebaseInitializer
import com.firebase.core.di.platformModule
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.*
import org.koin.mp.KoinPlatform

@Configuration
@Module
class AppModule

@KoinApplication
object KoinApp

fun initKoinMitul(configuration : KoinAppDeclaration? = null) {
    val koinApp = KoinApp.startKoin {
        modules(
            AppModule().module,
            platformModule
        )
        configuration?.invoke(this)
    }

    KoinPlatform.getKoin().get<FirebaseInitializer>().initialize()
}