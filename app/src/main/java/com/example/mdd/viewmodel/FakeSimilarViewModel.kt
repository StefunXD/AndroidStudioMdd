package com.example.mdd.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mdd.model.DataFakeSimilar
import com.example.mdd.utils.FakeSimilarJsonUtils


class FakeSimilarViewModel(application: Application) : AndroidViewModel(application){
    private val _similarMusicList = MutableLiveData<List<DataFakeSimilar>>()
    val similarMusicList: LiveData<List<DataFakeSimilar>> = _similarMusicList

    init{
        loadSimilarMusic()
    }

    private fun loadSimilarMusic(){
        val jsonString =
           FakeSimilarJsonUtils.getJsonDataFromAsset(getApplication(), "fakesimilarmusic.json")
        val list = if (jsonString != null) {
            FakeSimilarJsonUtils.parseFakeSimilarMusicJson(jsonString)
        } else {
            emptyList()
        }
        _similarMusicList.value = list
    }
}