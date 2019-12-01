package com.sf.votestreams.home

import androidx.lifecycle.MutableLiveData
import com.sf.votestreams.common.DisposableViewModel
import com.sf.votestreams.home.dataModels.Post
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val model: HomeModel) : DisposableViewModel() {

    val isPostLoaded = MutableLiveData<Boolean>()
    val isLoadingPost = MutableLiveData<Boolean>()
    val hasLoadingPostError = MutableLiveData<Boolean>()
    val loadingPostError = MutableLiveData<String>()
    val post = MutableLiveData<Post>()

    init {
        isPostLoaded.value = false
        isLoadingPost.value = false
        hasLoadingPostError.value = false
        loadingPostError.value = ""
    }

    fun getPost() {
        isLoadingPost.value = true
        val observable = model.getPost(1)
        val disposable = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    post.value = it
                    isLoadingPost.value = false
                    isPostLoaded.value = true
                },
                onError = {
                    isPostLoaded.value = false
                    isLoadingPost.value = false
                    loadingPostError.value = it.localizedMessage
                }
            )
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}