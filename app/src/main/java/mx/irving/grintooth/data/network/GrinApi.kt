package mx.irving.grintooth.data.network

import io.reactivex.Observable
import mx.irving.grintooth.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GrinApi {

    /**
     * Return a list of all devices saved in the service
     */
    @GET(BuildConfig.PATH_GET_DEVICES)
    fun getDevices(): Observable<List<DeviceResponse>>

    @POST(BuildConfig.PATH_ADD_DEVICE)
    fun addDevice(@Body device: DeviceRequest): Observable<AddDeviceResponse>

    companion object {
        val instance: GrinApi by lazy {
            val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            retrofit.create(GrinApi::class.java)
        }
    }
}
