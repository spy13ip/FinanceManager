package com.spy13.financemanager.views.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.spy13.financemanager.R;
import com.spy13.financemanager.models.entities.Purse;

import java.util.List;

public class PurseListItemAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Purse> list;

    public PurseListItemAdapter(LayoutInflater inflater, List<Purse> list) {
        this.inflater = inflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.template_purse_list_item, parent, false);
        }
        Purse purse = list.get(position);
        ((TextView) view.findViewById(R.id.purseListItemTemplate_purseName)).setText(purse.getName());
        ((TextView) view.findViewById(R.id.purseListItemTemplate_currencyCode)).setText(purse.getCurrency().getCode());
        ((TextView) view.findViewById(R.id.purseListItemTemplate_currencyName)).setText(purse.getCurrency().getName());
        return view;
    }
}
