package com.spy13.financemanager.views;

import com.spy13.financemanager.domain.entity.Purse;

import java.util.List;

public interface IPurseListView {
    void progressVisible(boolean value);
    void progressWait(boolean value);
    void progressErrorVisible(boolean value);
    void progressCanceledVisible(boolean value);
    void nothingDataVisible(boolean value);
    void setPurses(List<Purse> purses);
    void showPurse(Purse purse);
}
