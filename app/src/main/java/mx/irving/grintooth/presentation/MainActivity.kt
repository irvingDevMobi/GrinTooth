package mx.irving.grintooth.presentation

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import mx.irving.grintooth.R
import mx.irving.grintooth.domain.SavedDevicesViewModel
import mx.irving.grintooth.utils.applyOnUi

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val model = ViewModelProviders.of(this).get(SavedDevicesViewModel::class.java)
        model.getDevices()
                .applyOnUi()
                .subscribe(
                        {},
                        {}
                )
    }
}
