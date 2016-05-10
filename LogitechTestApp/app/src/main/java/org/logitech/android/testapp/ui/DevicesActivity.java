package org.logitech.android.testapp.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.logitech.android.testapp.R;
import org.logitech.android.testapp.adapter.DevicesListAdapter;
import org.logitech.android.testapp.device.DevicesPresenter;
import org.logitech.android.testapp.device.IDevicesView;
import org.logitech.android.testapp.model.Devices;

import org.logitech.android.testapp.utils.Utils;

import java.util.ArrayList;


public class DevicesActivity extends AppCompatActivity implements IDevicesView {

    private static String TAG = DevicesActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private DevicesListAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private TextView mEmptyListTextView;
    private DevicesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mEmptyListTextView = (TextView) findViewById(R.id.list_empty_textview);

        presenter = new DevicesPresenter(this);


        if(Utils.isNetworkAvailable(DevicesActivity.this)) {
            mProgressDialog = new ProgressDialog(DevicesActivity.this);
            mProgressDialog.setMessage("Fetching data...");
            mProgressDialog.show();
            presenter.loadDeviceData();

        } else {
            Utils.showToast(DevicesActivity.this, "Please check internet connection.");
            mEmptyListTextView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void populateList(ArrayList<Devices> deviceList) {

        if ((deviceList != null && deviceList.size() > 0)) {

            mAdapter = new DevicesListAdapter(deviceList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.setAdapter(mAdapter);
            mEmptyListTextView.setVisibility(View.GONE);
            mProgressDialog.dismiss();

        } else {
            Log.w(TAG, "List is empty");
            mEmptyListTextView.setVisibility(View.VISIBLE);
            if(mProgressDialog != null) {
                mProgressDialog.dismiss();
            }
        }
    }

    @Override
    public void showError() {
        Utils.showToast(this, "Communication problem");
        mEmptyListTextView.setVisibility(View.VISIBLE);
        if(mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
