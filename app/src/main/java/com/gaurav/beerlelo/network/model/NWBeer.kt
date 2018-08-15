package com.gaurav.beerlelo.network.model

import android.text.TextUtils
import com.gaurav.beerlelo.ui.model.Beer
import com.google.gson.annotations.SerializedName

internal class NWBeer(

        @SerializedName("id")
        val id: Int,
        @SerializedName("abv")
        val alcoholContent: String,
        @SerializedName("ibu")
        val bitterUnit: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("style")
        val style: String,
        @SerializedName("ounces")
        val ounces: Float
)

internal fun NWBeer.convertToBeer(): Beer {

    val alcoholContent =
            if (TextUtils.isEmpty(alcoholContent))
                0F
            else
                alcoholContent.toFloat()

    return Beer(
            id,
            name,
            alcoholContent,
            bitterUnit,
            style,
            ounces)
}

