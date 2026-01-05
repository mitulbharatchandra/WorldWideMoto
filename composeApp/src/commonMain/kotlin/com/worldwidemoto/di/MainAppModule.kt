package com.worldwidemoto.di

import com.worldwidemoto.AppViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainAppModule = module {
    viewModelOf(::AppViewModel)
}