package org.logitech.android.testapp.device;

import org.logitech.android.testapp.model.Devices;

import java.util.ArrayList;

/**
 * 
 */
public interface IDevicesView {
    void populateList(ArrayList<Devices> deviceList);
    void showError();
}
