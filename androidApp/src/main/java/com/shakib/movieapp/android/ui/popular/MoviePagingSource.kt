package com.shakib.movieapp.android.ui.popular

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shakib.movieapp.data.dto.Movie
import com.shakib.movieapp.domain.UseCase
import kotlinx.coroutines.flow.last

class MoviePagingSource(private val useCase: UseCase) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPage = params.key ?: 1
        val result = useCase.getPopularMovies(page = params.key ?: 1).last()
        val currentPage = result.payload?.page ?: 1
        val pageSize = result.payload?.total_pages ?: 1
        val list = result.payload?.results ?: emptyList()
        return getReturn(
            throwable = result.error,
            list = list,
            nextPage = nextPage,
            pageSize = pageSize,
            currentPage = currentPage,
            serverErrorMessage = result.error?.message
        )
    }

    private fun <T : Any> getReturn(
        throwable: Throwable?,
        list: List<T>,
        serverErrorMessage: String?,
        nextPage: Int,
        pageSize: Int,
        currentPage: Int
    ): LoadResult<Int, T> {
        return if (throwable == null) {
            val prevKey = if (nextPage == 1) null else nextPage - 1
            val nextKey = if (pageSize == currentPage) null else currentPage + 1
            if (list.isNotEmpty()) LoadResult.Page(
                data = list,
                prevKey = prevKey,
                nextKey = nextKey
            )

            //handle caught error by returning server message
            else if (!serverErrorMessage.isNullOrEmpty())
                LoadResult.Error(Exception(serverErrorMessage))

            //handle unpredictable error
            else LoadResult.Error(Exception("No data available"))

        } else run {
            LoadResult.Error(Exception(throwable.message.toString()))
        }
    }
}
