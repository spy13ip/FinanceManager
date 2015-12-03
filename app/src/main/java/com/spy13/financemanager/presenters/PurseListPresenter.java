package com.spy13.financemanager.presenters;

import android.os.AsyncTask;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.models.entities.Purse;
import com.spy13.financemanager.models.services.IPurseService;
import com.spy13.financemanager.views.IPurseListView;

import java.util.List;

import javax.inject.Inject;

public class PurseListPresenter extends PresenterBase<IPurseListView> {
    private IPurseService purseService;

    private boolean isWorking;

    private List<Purse> purseList;
    private boolean purseListLoading;

    private PurseListLoadTask purseListLoadTask;

    @Inject
    public PurseListPresenter(IPurseService purseService) {
        Common.log(this, "PurseListPresenter");
        this.purseService = purseService;
    }

    public void onCreate() {
        Common.log(this, "onCreate");
        purseListLoadTask = new PurseListLoadTask();
        purseListLoadTask.execute();
    }

    public void onCreateView() {
        Common.log(this, "onCreateView");
        isWorking = true;
        if (purseList != null)
            getView().setPurseList(purseList);
        if (purseListLoading)
            getView().purseListProgressStart();
    }

    public void onDestroyView() {
        Common.log(this, "onDestroyView");
        isWorking = false;
    }

    public void onDestroy() {
        Common.log(this, "onDestroy");
        if(purseListLoadTask != null)
            purseListLoadTask.cancel(true);
    }

    public void onClickPurse(Purse purse) {
        Common.log(this, "onClickPurse");
        getView().showPurse(purse);
    }

    private class PurseListLoadTask extends AsyncTask<Void, Void, List<Purse>> {
        @Override
        protected void onPreExecute() {
            Common.log(this, "onPreExecute");
            super.onPreExecute();
            purseListLoading = true;
            if (!isWorking) return;
            getView().purseListProgressStart();
        }

        @Override
        protected List<Purse> doInBackground(Void... params) {
            Common.log(this, "doInBackground");
            try {
                Thread.sleep(5000);
                if (isCancelled()) return null;
                List<Purse> result = purseService.get();
                return result;
            } catch (InterruptedException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Purse> result) {
            Common.log(this, "onPostExecute");
            super.onPostExecute(result);
            purseListLoadTask = null;
            purseListLoading = false;
            purseList = result;
            if (!isWorking) return;
            getView().purseListProgressEnd();
            getView().setPurseList(purseList);
        }

        @Override
        protected void onCancelled() {
            Common.log(this, "onCancelled");
            super.onCancelled();
            purseListLoadTask = null;
            purseListLoading = false;
            if (!isWorking) return;
            getView().purseListProgressEnd();
        }
    }
}
