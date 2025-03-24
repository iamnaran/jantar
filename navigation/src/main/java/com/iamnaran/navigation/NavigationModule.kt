package com.iamnaran.navigation

import com.iamnaran.info.infoModule
import org.koin.dsl.module

val navigationModule = module {

    includes(infoModule)

}