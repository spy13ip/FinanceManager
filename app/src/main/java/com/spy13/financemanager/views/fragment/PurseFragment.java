package com.spy13.financemanager.views.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.injection.InjectorProvider;
import com.spy13.financemanager.R;
import com.spy13.financemanager.domain.entity.Purse;
import com.spy13.financemanager.presenters.PursePresenter;
import com.spy13.financemanager.views.IPurseView;

import javax.inject.Inject;

public class PurseFragment extends Fragment implements IPurseView {
    @Inject
    PursePresenter presenter;

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
        initArguments();
        ((InjectorProvider)getActivity()).getInjector().inject(this);
        presenter.setView(this);
        presenter.initArguments(purseId);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Common.log(this, "onCreateView");
        View view = inflater.inflate(R.layout.purse_fragment, container, false);
        purseNameView = (TextView) view.findViewById(R.id.purseFragment_purseName);
        currencyCodeView = (TextView) view.findViewById(R.id.purseFragment_currencyCode);
        currencyNameView = (TextView) view.findViewById(R.id.purseFragment_currencyName);
        presenter.onCreateView();
        return view;
    }

    @Override
    public void onDestroyView() {
        Common.log(this, "onDestroyView");
        super.onDestroyView();
        presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Common.log(this, "onDestroy");
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void setPurse(Purse purse) {
        Common.log(this, "setPurse");
        purseNameView.setText(purse.getName());
        currencyCodeView.setText(purse.getCurrency().getCode());
        currencyNameView.setText(purse.getCurrency().getName());
    }
}
