package com.test.loading.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.loading.model.Memes
import com.test.loading.model.MemesService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel :ViewModel(){

    val memesList = MutableLiveData<List<Memes>>()
    val apiError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    private  val memesService = MemesService()
    private val dispoable = CompositeDisposable()

    private fun loadData(){

        loading.value =true
        dispoable.add(
            memesService.getMemesAPIData()
                    ?.subscribeOn(Schedulers.newThread())
                    ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeWith(object: DisposableSingleObserver<List<Memes>>(){
                    override fun onSuccess(value: List<Memes>?) {
                                memesList.value = value
                               apiError.value = false
                                loading.value = false
                    }
                    override fun onError(e: Throwable?) {

                        apiError.value = true
                        loading.value = false
                    }
                })
        )
    }

    fun refresh(){
        loadData()
    }

    override fun onCleared() {
        super.onCleared()
        dispoable.clear()
    }
}