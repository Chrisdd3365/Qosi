package com.christophedurand.qosi.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class QosiParseJSONData : Serializable {

    data class Result(
        @SerializedName("results")
        val results: ArrayList<User>
    ) : Serializable

    data class User(
        @SerializedName("name")
        val name: Name,

        @SerializedName("location")
        val location: Location,

        @SerializedName("email")
        val email: String,

        @SerializedName("picture")
        val picture: Picture

    ) : Serializable

    data class Name(
        @SerializedName("first")
        val first: String,

        @SerializedName("last")
        val last: String
    ) : Serializable

    data class Location(
        @SerializedName("street")
        val street: Street
    ) : Serializable

    data class Street(
        @SerializedName("number")
        val number: Int,

        @SerializedName("name")
        val name: String
    ) : Serializable

    data class Picture(
        @SerializedName("large")
        val large: String,

        @SerializedName("thumbnail")
        val thumbnail: String
    ) : Serializable

}
