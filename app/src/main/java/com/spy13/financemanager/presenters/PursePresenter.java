package com.spy13.financemanager.presenters;

import com.spy13.financemanager.Common;
import com.spy13.financemanager.dao.DaoException;
import com.spy13.financemanager.domain.entity.Purse;
import com.spy13.financemanager.dao.impl.PurseDao;
import com.spy13.financemanager.views.IPurseView;

import javax.inject.Inject;

public class PursePresenter extends FragmentPresenterBase<IPurseView> {
    private PurseDao purseService;

    private int purseId;

    private Purse purse;

    @Inject
    public PursePresenter(PurseDao purseService) {
        Common.log(this, "PursePresenter");
        this.purseService = purseService;
    }

    public void initArguments(int purseId){
        Common.log(this, "initArguments");
        this.purseId = purseId;
    }

    @Override
    public void onCreate() {
        try {
            purse = purseService.getById(purseId);
        } catch (DaoException e) {
            purse = null;
        }
        getView().setPurse(purse);
    }

}
