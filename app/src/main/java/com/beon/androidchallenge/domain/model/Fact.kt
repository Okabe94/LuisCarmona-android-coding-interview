package com.beon.androidchallenge.domain.model

import com.google.gson.annotations.SerializedName

class Fact {
    @SerializedName("text")
    var text: String? = null
    @SerializedName("number")
    var number: Long? = null
    @SerializedName("found")
    var found: Boolean? = null
    @SerializedName("type")
    var type: String? = null
}