package com.christophedurand.qosi.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.christophedurand.qosi.R
import com.christophedurand.qosi.model.QosiParseJSONData
import com.squareup.picasso.Picasso


class QosiRecyclerViewAdapter(
    private val dataSet: QosiParseJSONData.Result,
    private val mListener: UsersListInterface
) : RecyclerView.Adapter<QosiRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // FIXME:  Can use Butterknife to bind view and avoid this warning
        val profilePictureImageView: ImageView
        val firstNameTextView: TextView
        val lastNameTextView: TextView
        val deleteImageButton: ImageButton

        init {
            // Define click listener for the ViewHolder's View.
            profilePictureImageView = view.findViewById(R.id.profile_image_view)
            firstNameTextView = view.findViewById(R.id.first_name_text_view)
            lastNameTextView = view.findViewById(R.id.last_name_text_view)
            deleteImageButton = view.findViewById(R.id.delete_image_button)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.user_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val user: QosiParseJSONData.User = dataSet.results[position]

        // Get element from the data set at this position and replace the
        // contents of the view with that element
        Picasso.get().load(user.picture.thumbnail).into(viewHolder.profilePictureImageView)

        viewHolder.firstNameTextView.text = user.name.first
        viewHolder.lastNameTextView.text = user.name.last

        viewHolder.itemView.setOnClickListener {
            mListener.onClickUser(user)
        }

        viewHolder.deleteImageButton.setOnClickListener {
            mListener.onDeleteUser(user)
        }
    }

    // Return the size of the data set (invoked by the layout manager)
    override fun getItemCount() = dataSet.results.size

}