package mx.irving.grintooth.mvvmviewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import mx.irving.grintooth.mvvmdata.Device
import mx.irving.grintooth.mvvmdata.DeviceGateway
import mx.irving.grintooth.mvvmdata.mappers.DeviceResponseMapper
import mx.irving.grintooth.mvvmdata.network.GrinApi

class ServiceDevicesViewModel : ViewModel() {

    private val deviceGateway by lazy { DeviceGateway(GrinApi.instance, DeviceResponseMapper()) }
    private var deviceList: MutableList<Device> = mutableListOf()
    private var sortAscendente = false

    fun getDevices(): Observable<List<Device>> {
        return deviceGateway.getDevices()
                .map {
                    deviceList = it.value?.toMutableList() ?: mutableListOf()
                    deviceList
                }
    }

    fun getDevicesSorted(): Observable<List<Device>> {
        //TODO: update by Date, not string value
        sortAscendente = !sortAscendente
        if (sortAscendente) deviceList.sortBy { it.creationDate }
        else deviceList.sortByDescending { it.creationDate }
        return Observable.just(deviceList)
    }
}
