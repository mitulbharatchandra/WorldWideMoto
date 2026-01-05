package com.worldwidemoto.di
import com.auth.data.di.authDataModule
import com.auth.domain.di.authDomainModule
import com.auth.presentation.di.authPresentationModule
import com.firebase.core.di.FirebaseInitializer
import com.firebase.core.di.firebaseCoreModule
import com.google.sigin.di.signInWithGoogleModule
import com.home.presentation.di.homePresentationModule
import com.worldwidemoto.firebase.auth.di.firebaseAuthModule
import org.koin.core.annotation.Configuration
import org.koin.core.annotation.KoinApplication
import org.koin.core.annotation.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module
import org.koin.ksp.generated.startKoin
import org.koin.mp.KoinPlatform

@Configuration
@Module
class AppModule

@KoinApplication
object KoinApp

fun initKoinMitul(configuration : KoinAppDeclaration? = null) {
    KoinApp.startKoin {
        modules(
            AppModule().module,
            mainAppModule,
            firebaseCoreModule,
            firebaseAuthModule,
            authDomainModule,
            authDataModule,
            authPresentationModule,
            signInWithGoogleModule,
            homePresentationModule
        )
        configuration?.invoke(this)
    }

    KoinPlatform.getKoin().get<FirebaseInitializer>().initialize()
}