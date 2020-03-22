package com.barnes.infopumps.viewModels.models

import com.google.gson.annotations.SerializedName

data class ProductScan(@SerializedName("subcategory_id") val category: String,
                       @SerializedName("product_id") val productId: String)