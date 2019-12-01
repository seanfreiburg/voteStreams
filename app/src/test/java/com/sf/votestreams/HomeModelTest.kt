package com.sf.votestreams

import com.sf.votestreams.home.HomeModel
import com.sf.votestreams.home.PostClient
import com.sf.votestreams.home.dataModels.Post
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeModelTest {

    @Mock
    private lateinit var mockPostClient: PostClient

    @Test
    fun getPosts_isCorrect() {
        Mockito.doReturn(Single.just(listOf(Post(1, 1, "title", "body"))))
            .`when`(mockPostClient).getPosts()

        val homeModel = HomeModel(mockPostClient)
        homeModel
            .getPosts()
            .test()
            .assertValue(listOf(Post(1, 1, "title", "body")))
    }

    @Test
    fun getPost_isCorrect() {
        Mockito.doReturn(Single.just(Post(1, 1, "title", "body")))
            .`when`(mockPostClient).getPost(1)

        val homeModel = HomeModel(mockPostClient)
        homeModel
            .getPost(1)
            .test()
            .assertValue(Post(1, 1, "title", "body"))
    }
}
