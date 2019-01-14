package mx.irving.grintooth.mvvmview

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import mx.irving.grintooth.R
import mx.irving.grintooth.mvvmdata.Device
import mx.irving.grintooth.mvvmviewmodels.DiscoverDevicesViewModel
import mx.irving.grintooth.utils.applyOnUi
import timber.log.Timber
import java.lang.ref.WeakReference

class MainActivity : BaseActivity<DiscoverDevicesViewModel>() {

    override val viewModelClazz = DiscoverDevicesViewModel::class.java

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice =
                            intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    Toast.makeText(this@MainActivity, device.name, Toast.LENGTH_SHORT).show()
                }
                else -> Timber.d(intent.action)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(receiver, IntentFilter(BluetoothDevice.ACTION_FOUND))
        viewModel.bluetoothManager.contextWeak = WeakReference(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION -> {
                if (grantResults.isNotEmpty()
                    && grantResults.first() == PackageManager.PERMISSION_GRANTED) {

                }
            }
        }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }

    override fun addDisposables() {
        addDisposable(
                viewModel.getViewState()
                        .applyOnUi()
                        .subscribe(
                                { drawViewState(it) },
                                { Timber.e(it) }
                        )
        )
        addDisposable(
                viewModel.getDiscoveredDevices()
                        .applyOnUi()
                        .subscribe(
                                { drawDiscoveredDevices(it) },
                                { Timber.e(it) }
                        )
        )
        if (!viewModel.hasLocationPermission) viewModel.startDeviceDiscovery()
    }

    fun drawViewState(state: DiscoverDevicesViewModel.ViewState) {
        discoverLoader.visibility = if (state.showingLoader) View.VISIBLE else View.GONE
        discoverNoBluetoothMessage.visibility =
                if (state.showingBluetoothNoSupportedMessage) View.VISIBLE else View.GONE
        if (state.showingDialogToEnableBluetooth) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BT)
        }
        if (state.showingDialogToLocationPermission) {
            ActivityCompat.requestPermissions(this,
                                              arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                                              REQUEST_LOCATION)
        }
    }

    fun drawDiscoveredDevices(devices: List<Device>) {

    }

    companion object {
        const val REQUEST_ENABLE_BT = 4444
        const val REQUEST_LOCATION = 4455
    }
}
