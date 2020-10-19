package com.example.clickdebouncer

import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test


@kotlinx.coroutines.ExperimentalCoroutinesApi
class DebounceKtTest {


    @Test
    fun `should call only once when time between calls is shorter than debounce delay`() =
        runBlockingTest {
            // given
            val debounceTime = 500L
            val timeBetweenCalls = 200L
            val firstParam = 1
            val secondParam = 2
            val testFun = mock<(Int) -> Unit> {
                onGeneric { invoke(any()) } doReturn Unit
            }

            val debouncedTestFun = debounce(
                debounceTime,
                this,
                testFun
            )

            // when
            debouncedTestFun(firstParam)
            advanceTimeBy(timeBetweenCalls)
            debouncedTestFun(secondParam)

            // then
            verify(testFun, times(1)).invoke(any())
        }

    @Test
    fun `should call every time with proper params when time between calls is longer than debounce delay`() =
        runBlockingTest {
            // given
            val debounceTime = 500L
            val timeBetweenCalls = 1000L
            val firstParam = 1
            val secondParam = 2
            val testFun = mock<(Int) -> Unit> {
                onGeneric { invoke(any()) } doReturn Unit
            }

            val debouncedTestFun = debounce(
                debounceTime,
                this,
                testFun
            )

            // when
            debouncedTestFun(firstParam)
            advanceTimeBy(timeBetweenCalls)
            debouncedTestFun(secondParam)

            // then
            verify(testFun).invoke(firstParam)
            verify(testFun).invoke(secondParam)
        }
}