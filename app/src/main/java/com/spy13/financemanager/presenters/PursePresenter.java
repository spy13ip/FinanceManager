package com.spy13.financemanager.presenters;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.models.entities.Purse;
import com.spy13.financemanager.models.services.PurseService;
import com.spy13.financemanager.views.IPurseView;

import javax.inject.Inject;

public class PursePresenter extends PresenterBase<IPurseView> {
    private PurseService purseService;

    private Purse purse;

    @Inject
    public PursePresenter(PurseService purseService) {
        Common.log(this, "PursePresenter");
        this.purseService = purseService;
    }

    public void onCreate() {
        Common.log(this, "init");
    }

    public void init(int purseId){
        Common.log(this, "init");
        purse = purseService.getById(purseId);
        getView().setPurse(purse);
    }
}
