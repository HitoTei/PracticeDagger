package com.example.practicedagger

import android.app.Application


class MyApplication : Application() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        appComponent = DaggerApplicationComponent.builder()
            .roomModule(RoomModule(this))
            .viewModelModule(ViewModelModule())
            .build()
        appComponent.inject(this)
        super.onCreate()
    }
}