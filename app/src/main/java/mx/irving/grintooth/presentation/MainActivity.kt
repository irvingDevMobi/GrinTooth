package mx.irving.grintooth.presentation

import android.bluetooth.BluetoothAdapter
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import mx.irving.grintooth.R
import mx.irving.grintooth.domain.DiscoverDevicesViewModel
import mx.irving.grintooth.utils.applyOnUi

class MainActivity : BaseActivity<DiscoverDevicesViewModel>() {

    override val viewModelClazz = DiscoverDevicesViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun addDisposables() {
        addDisposable(
                viewModel.getHasBluetooth()
                        .applyOnUi()
                        .subscribe(
                                {
                                    if (it) {

                                    } else {
                                        showNoBluetoothSupport()
                                    }
                                },
                                {
                                    showNoBluetoothSupport()
                                }
                        )
        )
        verifyBluetoothSupport()
    }

    private fun verifyBluetoothSupport() {
        if (!viewModel.mobileHasBluetooth) {
            viewModel.mobileHasBluetooth = BluetoothAdapter.getDefaultAdapter() != null
        }
    }

    private fun showNoBluetoothSupport() {
        discoverNoBluetoothMessage.visibility = View.VISIBLE
    }

}
