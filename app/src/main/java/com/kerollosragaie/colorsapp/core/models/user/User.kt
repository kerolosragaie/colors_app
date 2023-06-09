package com.kerollosragaie.colorsapp.core.models.user

import com.google.gson.annotations.SerializedName


data class User(
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    var address: Address,
    var phone: String,
    var website: String,
    var company: Company
)