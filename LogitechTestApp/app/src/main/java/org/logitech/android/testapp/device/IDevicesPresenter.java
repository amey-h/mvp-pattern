package org.logitech.android.testapp.device;

import org.logitech.android.testapp.model.Devices;

import java.util.ArrayList;

/**
 * 
 */
public interface IDevicesPresenter {
    void onDeviceDataLoad(ArrayList<Devices> deviceList);
    void onDataLoadError();
}
