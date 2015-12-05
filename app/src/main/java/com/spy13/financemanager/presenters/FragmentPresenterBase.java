package com.spy13.financemanager.presenters;

import com.spy13.financemanager.Common;

public class FragmentPresenterBase<T> extends PresenterBase<T> {
    private boolean _isWorking;

    protected boolean isWorking() {
        return _isWorking;
    }

    public void onCreate() {
        Common.log(this, "onCreate");
    }

    public void onCreateView() {
        Common.log(this, "onCreateView");
        _isWorking = true;
    }

    public void onDestroyView() {
        Common.log(this, "onDestroyView");
        _isWorking = false;
    }

    public void onDestroy() {
        Common.log(this, "onDestroy");
    }
}
