package com.christophedurand.qosi.controllers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.christophedurand.qosi.R
import com.christophedurand.qosi.model.QosiGetData
import com.christophedurand.qosi.model.QosiParseJSONData
import com.christophedurand.qosi.views.QosiRecyclerViewAdapter
import com.christophedurand.qosi.views.UsersListInterface


class MainActivity : Activity(), UsersListInterface {

    //MARK: - PROPERTIES
    val BUNDLE_EXTRA_USER_DETAILS = "USER_DETAILS"

    private lateinit var qosiGetData: QosiGetData
    private lateinit var mUsersList: QosiParseJSONData.Result
    private lateinit var mAdapter: QosiRecyclerViewAdapter
    private lateinit var mRecyclerView: RecyclerView


    //MARK: - VIEW LIFE CYCLE
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        mUsersList = QosiParseJSONData.Result(ArrayList())

        val mLinearLayoutManager = LinearLayoutManager(this)

        mAdapter = QosiRecyclerViewAdapter(mUsersList, this)

        mRecyclerView = findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager = mLinearLayoutManager

        // GET ASYNCHRONOUSLY JSON DATA
        qosiGetData = QosiGetData(mUsersList, mAdapter, mRecyclerView, this)
        qosiGetData.getUsersData()

        // SET DIVIDER TO SEPARATE THE ITEMS FROM EACH OTHER IN THE RECYCLER VIEW
        val decorator = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
        mRecyclerView.addItemDecoration(decorator)

    }

    //-- METHODS
    fun updateList(usersList: QosiParseJSONData.Result) {
        mUsersList = usersList
        mAdapter.notifyDataSetChanged()
    }


    //MARK: - UsersListInterface METHODS
    override fun onClickUser(user: QosiParseJSONData.User) {
        val intent = Intent(this, UserDetailsActivity::class.java).apply {
            putExtra(BUNDLE_EXTRA_USER_DETAILS, user)
        }
        startActivity(intent)
    }

    override fun onDeleteUser(user: QosiParseJSONData.User) {
        //FIXME : Doesn't work as expected -> when we log it, remove boolean is false
        Log.d("mUsersList is ", mUsersList.toString())
        Log.d("mUsersList size is ", mUsersList.results.size.toString())
        mUsersList.results.remove(user)
        Log.d("remove check boolean ", mUsersList.results.remove(user).toString())
        //Refresh data
        updateList(mUsersList)
    }

}