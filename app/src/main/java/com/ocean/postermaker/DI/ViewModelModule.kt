package com.oceanmtech.dmt.DI

import com.ocean.postermaker.UI.DashboardModule.SettingsModule.faqViewModel
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.getProfileViewModel
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.getReligionsViewModel
import com.ocean.postermaker.UI.DashboardModule.SettingsModule.updateProfileViewModel
import com.ocean.postermaker.UI.LoginModule.Language.LanguageViewModel
import com.ocean.postermaker.UI.LoginModule.Language.UpdateLanguageViewModel
import com.ocean.postermaker.UI.LoginModule.Location.CityViewModel
import com.ocean.postermaker.UI.LoginModule.Location.CountryViewModel
import com.ocean.postermaker.UI.LoginModule.Location.StateViewModel
import com.ocean.postermaker.UI.LoginModule.Location.UpdateLocationViewModel
import com.ocean.postermaker.UI.LoginModule.LoginViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
//    viewModel { SplashViewModel(androidContext(), get(), get()) }
    viewModel { LoginViewModel(androidContext(), get(), get()) }
    viewModel { LanguageViewModel(androidContext(), get(), get()) }
    viewModel { UpdateLanguageViewModel(androidContext(), get(), get()) }
    viewModel { UpdateLocationViewModel(androidContext(), get(), get()) }
    viewModel { CountryViewModel(androidContext(), get(), get()) }
    viewModel { StateViewModel(androidContext(), get(), get()) }
    viewModel { CityViewModel(androidContext(), get(), get()) }
    viewModel { getProfileViewModel(androidContext(), get(), get()) }
    viewModel { updateProfileViewModel(androidContext(), get(), get()) }
    viewModel { getReligionsViewModel(androidContext(), get(), get()) }
    viewModel { faqViewModel(androidContext(), get(), get()) }
//    viewModel { OTPViewModel(androidContext(), get(), get()) }
//    viewModel { AddBusinessViewModel(androidContext(), get(), get()) }
//    viewModel { UserBusinessViewModel(androidContext(), get(), get()) }
//    viewModel { BusinessCatViewModel(androidContext(), get(), get()) }
//    viewModel { AppLanguageViewModel(androidContext(), get(), get()) }
//    viewModel { UserLanguageViewModel(androidContext(), get(), get()) }
//    viewModel { CountryViewModel(androidContext(), get(), get()) }
//    viewModel { StateAndCityViewModel(androidContext(), get(), get()) }
//    viewModel { ProfileViewModel(androidContext(), get(), get()) }
//    viewModel { ReligionViewModel(androidContext(), get(), get()) }
//    viewModel { DashboardViewModel(androidContext(), get(), get()) }
//    viewModel { PostEditorViewModel(androidContext(), get(), get()) }
//    viewModel { PremiumViewModel(androidContext(), get(), get()) }
//    viewModel { FramePreviewViewModel(androidContext(), get(), get()) }
//    viewModel { VisitingCardViewModel(androidContext(), get(), get()) }
//    viewModel { CustomViewModel(androidContext(), get(), get()) }
//    viewModel { MotivationalPostViewModel(androidContext(), get(), get()) }
//    viewModel { PaymentHistoryViewModel(androidContext(), get(), get()) }
//    viewModel { DBVCViewModel(androidContext(), get(), get()) }
//    viewModel { CustomFrameViewModel(androidContext(), get(), get()) }
//    viewModel { OtherViewModel(androidContext(), get(), get()) }
//    viewModel { DealsAndDiscountViewModel(androidContext(), get(), get()) }

}
