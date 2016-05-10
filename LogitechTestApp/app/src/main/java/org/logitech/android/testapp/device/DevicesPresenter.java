package org.logitech.android.testapp.device;

import org.logitech.android.testapp.model.Devices;

import java.util.ArrayList;

/**
 * 
 */
public class DevicesPresenter implements IDevicesPresenter {

    private IDevicesView view;
    private DevicesInteractor interactor;

    public DevicesPresenter(IDevicesView view) {
        this.view = view;
        this.interactor = new DevicesInteractor(this);
    }

    public void loadDeviceData(){
        interactor.loadDeviceData();
    }

    @Override
    public void onDeviceDataLoad(ArrayList<Devices> deviceList) {
        view.populateList(deviceList);
    }

    @Override
    public void onDataLoadError() {
        view.showError();
    }
}
