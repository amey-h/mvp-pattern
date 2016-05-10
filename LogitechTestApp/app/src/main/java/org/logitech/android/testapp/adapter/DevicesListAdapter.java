package org.logitech.android.testapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.logitech.android.testapp.R;
import org.logitech.android.testapp.model.Devices;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class DevicesListAdapter extends RecyclerView.Adapter<DevicesListAdapter.ViewHolder> {

    private ArrayList<Devices> deviceList;

    public DevicesListAdapter(ArrayList<Devices> devices) {
        deviceList = devices;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.row_name_textview);

        }
    }

    @Override
    public DevicesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_items, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DevicesListAdapter.ViewHolder holder, int position) {
        Devices device = deviceList.get(position);
        holder.nameTextView.setText(device.getName());
    }

    @Override
    public int getItemCount() {
        return deviceList.size();
    }
}
