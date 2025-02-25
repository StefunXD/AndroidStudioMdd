package com.example.mdd.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import com.example.mdd.model.DataUserComments


class UserCommentsViewModel(application: Application) : AndroidViewModel(application) {
    @RequiresApi(Build.VERSION_CODES.O)
    val comments: List<DataUserComments> = com.example.mdd.utils.UserCommentsJsonUtils.loadUserComments(application)
}