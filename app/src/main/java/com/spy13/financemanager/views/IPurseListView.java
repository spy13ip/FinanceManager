package com.spy13.financemanager.views;

import com.spy13.financemanager.models.entities.Purse;

import java.util.List;

public interface IPurseListView {
    void setPurseList(List<Purse> purseList);
    void purseListChanged();
    void purseListProgressStart();
    void purseListProgressEnd();
    void showPurse(Purse purse);
}