package com.iamnaran.home.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Info(
    @SerialName("detail")
    val detail: String,

    @SerialName("dosages")
    val dosages: Dosages,

    @SerialName("isMedicine")
    val isMedicine: Boolean,

    @SerialName("main_usages")
    val mainUsages: String,
    @SerialName("name")
    val name: String,

    @SerialName("other_details")
    val otherDetails: OtherDetails,

    @SerialName("secondary_usages")
    val secondaryUsages: String,

    @SerialName("storage_instructions")
    val storageInstructions: String,

    @SerialName("type")
    val type: String,

    @SerialName("warning")
    val warning: String
)

@Serializable
data class Dosages(
    @SerialName("adult")
    val adult: String,

    @SerialName("children")
    val children: String
)

@Serializable
data class OtherDetails(
    @SerialName("batch_number")
    val batchNumber: String,

    @SerialName("expiry_date")
    val expiryDate: String,

    @SerialName("logo")
    val logo: String,

    @SerialName("price")
    val price: String
)