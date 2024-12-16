package org.example.bookstore.di

import org.koin.core.context.GlobalContext.startKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}