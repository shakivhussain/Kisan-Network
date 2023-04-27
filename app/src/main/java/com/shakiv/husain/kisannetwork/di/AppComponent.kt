package com.shakiv.husain.kisannetwork.di

import android.content.Context
import com.shakiv.husain.kisannetwork.MainActivity
import com.shakiv.husain.kisannetwork.ui.home.ContactListFragment
import com.shakiv.husain.kisannetwork.ui.sendmessage.SendMessageFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(contactListFragment: ContactListFragment)
    fun inject(sendMessageFragment: SendMessageFragment)

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : AppComponent
    }

}