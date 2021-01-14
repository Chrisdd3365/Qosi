package com.christophedurand.qosi.controllers

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.christophedurand.qosi.R
import com.christophedurand.qosi.model.QosiParseJSONData
import com.squareup.picasso.Picasso


class UserDetailsActivity : Activity() {

    //MARK: - PROPERTIES
    private val BUNDLE_EXTRA_USER_DETAILS = "USER_DETAILS"


    //MARK: - VIEW LIFE CYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        // Get the Intent that started this activity and extract the user
        val user = intent.getSerializableExtra(BUNDLE_EXTRA_USER_DETAILS) as QosiParseJSONData.User?

        // Init UI in View Life Cycle
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_user_details)

        // Capture the layout's Texts Views and set the string as its text
        findViewById<TextView>(R.id.first_name_text_view).apply {
            text = user?.name?.first
        }

        findViewById<TextView>(R.id.last_name_text_view).apply {
            text = user?.name?.last
        }

        findViewById<TextView>(R.id.address_text_view).apply {
            val street = user?.location?.street
            text = street?.number.toString() + " " + street?.name
        }

        findViewById<TextView>(R.id.email_text_view).apply {
            text = user?.email
        }

        // Capture the layout's Image View and load the image from the URL string as its image
        val profilePictureImageView : ImageView = findViewById(R.id.profile_image_view)
        Picasso.get().load(user?.picture?.large).into(profilePictureImageView)

    }

}