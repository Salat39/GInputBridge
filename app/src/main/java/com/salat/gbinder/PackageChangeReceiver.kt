package com.salat.gbinder

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.salat.gbinder.coroutines.IoCoroutineScope
import com.salat.gbinder.entity.PackagesChangedEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PackageChangeReceiver() : BroadcastReceiver() {

    @Inject
    @IoCoroutineScope
    lateinit var scope: CoroutineScope

    override fun onReceive(context: Context, intent: Intent) {
        val packageName = intent.data?.schemeSpecificPart ?: return
        val replacing = intent.getBooleanExtra(Intent.EXTRA_REPLACING, false)

        val action = intent.action
        if (Intent.ACTION_PACKAGE_ADDED == action ||
            Intent.ACTION_PACKAGE_REMOVED == action ||
            Intent.ACTION_PACKAGE_CHANGED == action
        ) {
            scope.launch {
                GlobalState.devicePackagesChangedFlow.emit(
                    when {
                        Intent.ACTION_PACKAGE_ADDED == action ->
                            if (replacing) PackagesChangedEvent.Changed(packageName)
                            else PackagesChangedEvent.Added(packageName)

                        Intent.ACTION_PACKAGE_REMOVED == action ->
                            if (replacing) PackagesChangedEvent.Changed(packageName)
                            else PackagesChangedEvent.Removed(packageName)

                        else -> PackagesChangedEvent.Changed(packageName)
                    }
                )
            }
        }
    }
}
