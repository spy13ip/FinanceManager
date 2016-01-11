package com.spy13.financemanager.views.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.R;
import com.spy13.financemanager.domain.entity.Purse;
import com.spy13.financemanager.injection.InjectorProvider;
import com.spy13.financemanager.presenters.PurseListPresenter;
import com.spy13.financemanager.views.IPurseListView;
import com.spy13.financemanager.views.ShowPurseListener;
import com.spy13.financemanager.views.adapter.PurseListItemAdapter;

import java.util.List;

import javax.inject.Inject;

public class PurseListFragment extends Fragment implements IPurseListView {
    @Inject
    PurseListPresenter presenter;

    //Listeners
    private ShowPurseListener showPurseListener;

    //Views
    private ListView purseListView;
    private PurseListItemAdapter purseListItemAdapter;
    private ProgressBar purseListProgressView;

    public PurseListFragment() {
        setRetainInstance(true);
    }

    public static PurseListFragment createInstance() {
        return new PurseListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((InjectorProvider) getActivity()).getInjector().inject(this);
        presenter.setView(this);
        presenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.purse_list_fragment, container, false);
        purseListView = (ListView) view.findViewById(R.id.purseListFragment_purseList);
        purseListProgressView = (ProgressBar) view.findViewById(R.id.purseListFragment_purseListProgress);

        purseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.onClickPurse((Purse) purseListItemAdapter.getItem(position));
            }
        });
        presenter.onCreateView();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void progressVisible(boolean value) {
        purseListProgressView.setVisibility(value ? View.VISIBLE : View.GONE);
    }

    @Override
    public void progressWait(boolean value) {
    }

    @Override
    public void progressErrorVisible(boolean value) {

    }

    @Override
    public void progressCanceledVisible(boolean value) {

    }

    @Override
    public void nothingDataVisible(boolean value) {

    }

    @Override
    public void setPurses(List<Purse> purses) {
        purseListItemAdapter = new PurseListItemAdapter(getActivity().getLayoutInflater(), purses);
        purseListView.setAdapter(purseListItemAdapter);
//        purseListItemAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPurse(Purse purse) {
        Common.log(this, "showPurse");
        showPurseListener.showPurse(purse);
    }

    public void setShowPurseListener(ShowPurseListener showPurseListener) {
        this.showPurseListener = showPurseListener;
    }

    public boolean allowBackPressed() {
        return presenter.allowBackPressed();
    }
}
