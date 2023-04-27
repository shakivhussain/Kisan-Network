package com.shakiv.husain.kisannetwork.di

import androidx.lifecycle.ViewModel
import com.shakiv.husain.kisannetwork.ui.home.ContactViewModel
import com.shakiv.husain.kisannetwork.ui.viewmodel.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @ClassKey(AuthViewModel::class)
    @IntoMap
    abstract fun authViewModel(authViewModel: AuthViewModel): ViewModel

    @Binds
    @ClassKey(ContactViewModel::class)
    @IntoMap
    abstract fun contactViewModel(contactViewModel: ContactViewModel): ViewModel

}