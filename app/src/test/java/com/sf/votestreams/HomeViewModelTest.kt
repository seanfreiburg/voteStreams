package com.sf.votestreams

import com.sf.votestreams.home.HomeModel
import com.sf.votestreams.home.HomeViewModel
import com.sf.votestreams.home.dataModels.Post
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @Mock
    private lateinit var mockHomeModel: HomeModel

    @Test
    fun getPosts_isCorrect() {
        val testScheduler: Scheduler = TestScheduler()

        val post = Post(1, 1, "title", "body")
        Mockito.doReturn(Single.just(post))
            .`when`(mockHomeModel).getPosts()

        val homeViewModel = HomeViewModel(mockHomeModel, testScheduler, testScheduler)
        homeViewModel.getPost()
        assertEquals(homeViewModel.post, post)
    }
}
