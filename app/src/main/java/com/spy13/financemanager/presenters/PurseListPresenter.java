package com.spy13.financemanager.presenters;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.MyTask;
import com.spy13.financemanager.models.entities.Purse;
import com.spy13.financemanager.models.services.IPurseService;
import com.spy13.financemanager.views.IPurseListView;

import java.util.List;

import javax.inject.Inject;

public class PurseListPresenter extends FragmentPresenterBase<IPurseListView> {
    private IPurseService purseService;

    private List<Purse> purseList;
    private boolean purseListLoading;

    private PurseListLoadTask purseListLoadTask;

    @Inject
    public PurseListPresenter(IPurseService purseService) {
        Common.log(this, "PurseListPresenter");
        this.purseService = purseService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        purseListLoadTask = new PurseListLoadTask();
        purseListLoadTask.run(null);
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        if (purseList != null)
            getView().setPurseList(purseList);
        if (purseListLoading)
            getView().purseListProgressStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(purseListLoadTask != null)
            purseListLoadTask.cancel(true);
    }

    public void onClickPurse(Purse purse) {
        Common.log(this, "onClickPurse");
        getView().showPurse(purse);
    }

    private class PurseListLoadTask extends MyTask<Void, Void, List<Purse>> {
        @Override
        protected void onBefore() {
            Common.log(this, "onBefore");
            purseListLoading = true;
            if (!isWorking()) return;
            getView().purseListProgressStart();
        }

        @Override
        protected List<Purse> onBody(Void param) {
            Common.log(this, "doInBackground");
            try {
                Thread.sleep(5000);
                if (isCancelled()) return null;
                return purseService.get();
            } catch (InterruptedException e) {
                return null;
            }
        }

        @Override
        protected void onAfter(List<Purse> result, boolean isCanceled) {
            Common.log(this, "onAfter");
            purseListLoadTask = null;
            purseListLoading = false;
            if (!isCanceled) purseList = result;
            if (!isWorking()) return;
            getView().purseListProgressEnd();
            if (!isCanceled) getView().setPurseList(purseList);
        }
    }
}
