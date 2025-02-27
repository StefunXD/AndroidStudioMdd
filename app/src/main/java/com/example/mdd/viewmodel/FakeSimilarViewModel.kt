package com.example.mdd.viewmodel


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mdd.model.DataFakeSimilar
import com.example.mdd.utils.FakeSimilarJsonUtils


class FakeSimilarViewModel : ViewModel(){
    private val _similarMusicList = MutableLiveData<List<DataFakeSimilar>>()
    val similarMusicList: LiveData<List<DataFakeSimilar>> = _similarMusicList

    fun loadSimilarMusic(context: Context){
      val list = FakeSimilarJsonUtils.parseFakeSimilarMusicJson(context, "fakesimilarmusic.json")
        _similarMusicList.value = list
    }
}