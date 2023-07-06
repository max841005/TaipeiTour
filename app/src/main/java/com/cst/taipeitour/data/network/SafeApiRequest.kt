package com.cst.taipeitour.data.network

import com.cst.taipeitour.utils.ApiConnectFailException
import retrofit2.Call

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Call<T>) : T = call.invoke().execute().run {

        if (!isSuccessful) throw ApiConnectFailException("Api Connect Fail")

        if (body() == null) throw ApiConnectFailException("Api Connect Fail")

        body()!!
    }
}