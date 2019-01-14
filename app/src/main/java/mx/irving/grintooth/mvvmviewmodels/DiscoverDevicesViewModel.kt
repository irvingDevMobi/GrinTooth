package mx.irving.grintooth.mvvmviewmodels

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import mx.irving.grintooth.mvvmdata.Device
import mx.irving.grintooth.mvvmdata.IBluetoothManager
import mx.irving.grintooth.mvvmdata.platform.MyBluetoothManager

class DiscoverDevicesViewModel : ViewModel() {
    val bluetoothManager: IBluetoothManager by lazy {
        MyBluetoothManager()
    }

    private val viewStateSubject: BehaviorSubject<ViewState> = BehaviorSubject.create()
    private val viewState = ViewState()
    var hasLocationPermission = false
        private set

    fun startDeviceDiscovery() {
        if (bluetoothManager.isBluetoothSupported()) {
            if (bluetoothManager.isBluetoothEnable()) {
                viewState.showingDialogToEnableBluetooth = false
                if (bluetoothManager.hasLocationPermission()) {
                    hasLocationPermission = true
                    viewState.showingDialogToLocationPermission = false
                    bluetoothManager.startDeviceDiscovery()
                } else {
                    hasLocationPermission = false
                    viewState.showingDialogToLocationPermission = true
                }
            } else {
                viewState.showingDialogToEnableBluetooth = true
            }
        } else {
            viewState.showingBluetoothNoSupportedMessage = true
        }
        viewStateSubject.onNext(viewState)
    }

    fun getViewState(): Observable<ViewState> = viewStateSubject.map { it }

    fun getDiscoveredDevices(): Observable<List<Device>> {
        return Observable.just(listOf())
    }

    data class ViewState(
            var showingLoader: Boolean = false,
            var showingBluetoothNoSupportedMessage: Boolean = false,
            var showingDialogToEnableBluetooth: Boolean = false,
            var showingDialogToLocationPermission: Boolean = false,
            var showingDevicesDiscovered: Boolean = false
    )
}
