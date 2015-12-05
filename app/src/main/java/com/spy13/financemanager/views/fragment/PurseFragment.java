package com.spy13.financemanager.views.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.FinanceManagerApplication;
import com.spy13.financemanager.InjectorProvider;
import com.spy13.financemanager.R;
import com.spy13.financemanager.models.entities.Purse;
import com.spy13.financemanager.presenters.PursePresenter;
import com.spy13.financemanager.views.IPurseView;

import javax.inject.Inject;

public class PurseFragment extends Fragment implements IPurseView {
    @Inject
    PursePresenter pursePresenter;

    //Arguments
    private int purseId;

    //Views
    private TextView purseNameView;
    private TextView currencyCodeView;
    private TextView currencyNameView;

    public static PurseFragment createInstance(int purseId) {
        PurseFragment fragment = new PurseFragment();
        Bundle args = new Bundle();
        args.putInt("purseId", purseId);
        fragment.setArguments(args);
        return fragment;
    }

    private void initArguments() {
        purseId = getArguments().getInt("purseId");
    }

    public PurseFragment(){
        Common.log(this, "PurseFragment");
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Common.log(this, "onCreate");
        super.onCreate(savedInstanceState);
        ((InjectorProvider)getActivity()).getInjector().inject(this);
        pursePresenter.setView(this);
        initArguments();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Common.log(this, "onCreateView");
        View view = inflater.inflate(R.layout.purse_fragment, container, false);
        purseNameView = (TextView) view.findViewById(R.id.purseFragment_purseName);
        currencyCodeView = (TextView) view.findViewById(R.id.purseFragment_currencyCode);
        currencyNameView = (TextView) view.findViewById(R.id.purseFragment_currencyName);
        pursePresenter.init(purseId);
        return view;
    }

    @Override
    public void setPurse(Purse purse) {
        Common.log(this, "onClickPurse");
        purseNameView.setText(purse.getName());
        currencyCodeView.setText(purse.getCurrency().getCode());
        currencyNameView.setText(purse.getCurrency().getName());
    }
}
