package com.spy13.financemanager.presenters;

import com.spy13.financemanager.Common;

public class PresenterBase<T> {
    private T view;


    public T getView() {
        return view;
    }

    public void setView(T view) {
        Common.log(this, "setView");
        this.view = view;
    }
}
