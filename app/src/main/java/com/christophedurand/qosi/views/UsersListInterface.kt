package com.christophedurand.qosi.views

import com.christophedurand.qosi.model.QosiParseJSONData


interface UsersListInterface {
    fun onClickUser(user: QosiParseJSONData.User)
    fun onDeleteUser(user: QosiParseJSONData.User)
}