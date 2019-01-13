package mx.irving.grintooth.data.network

import mx.irving.grintooth.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface GrinServiceClient {
    companion object {
        val instance: GrinServiceClient by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retrofit.create(GrinServiceClient::class.java)
        }
    }
}
