package com.christophedurand.qosi.model

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.christophedurand.qosi.views.QosiRecyclerViewAdapter
import com.christophedurand.qosi.views.UsersListInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit


class QosiGetData(var mUsersList: QosiParseJSONData.Result,
                  var mAdapter : QosiRecyclerViewAdapter,
                  var mRecyclerView: RecyclerView,
                  private val listener: UsersListInterface) {

    fun getUsersData() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .build()

        // Create Service
        val service = retrofit.create(RandomUserGeneratorService::class.java)

        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getUsersList()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {   // Returns true if {@link #code()} is in the range [200..300)

                    // Convert raw JSON to JSON using GSON library
                    val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
                    val json = gsonBuilder.toJson(
                        JsonParser.parseString(
                            response.body()
                                ?.string()
                        )
                    )

                    mUsersList = Gson().fromJson(json, QosiParseJSONData.Result::class.java)
                    Log.d("mUsersList is ", mUsersList.toString())
                    Log.d("mUsersList size is ", mUsersList.results.size.toString())
                    mAdapter = QosiRecyclerViewAdapter(mUsersList, listener)
                    mRecyclerView.adapter = mAdapter

                } else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }
    }

}