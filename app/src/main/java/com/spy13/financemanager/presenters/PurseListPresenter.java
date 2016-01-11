package com.spy13.financemanager.presenters;

import android.os.AsyncTask;

import com.spy13.financemanager.dao.DaoException;
import com.spy13.financemanager.dao.IPurseDao;
import com.spy13.financemanager.domain.entity.Purse;
import com.spy13.financemanager.views.IPurseListView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class PurseListPresenter extends FragmentPresenterBase<IPurseListView> {
    private IPurseDao purseDao;

    private PursesLoadTask pursesLoadTask = new PursesLoadTask();

    @Inject
    public PurseListPresenter(IPurseDao purseDao) {
        this.purseDao = purseDao;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        pursesLoadTask = new PursesLoadTask();
        pursesLoadTask.start();
    }

    @Override
    public void onCreateView() {
        super.onCreateView();
        loadPurseStatusChange();
        loadPurseResultChange();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (pursesLoadTask != null)
            pursesLoadTask.cancel();
    }

    public boolean allowBackPressed() {
        if (pursesLoadTask.getStatus() == PursesLoadTask.STATUS_WAITING || pursesLoadTask.getStatus() == PursesLoadTask.STATUS_RUNNING) {
            pursesLoadTask.cancel();
            return false;
        } else {
            return true;
        }
    }

    public void onClickPurse(Purse purse) {
        getView().showPurse(purse);
    }

    private void loadPurseStatusChange() {
        if (!isWorking()) return;
        switch (pursesLoadTask.getStatus()) {
            case PursesLoadTask.STATUS_NOT_RUNNING:
                getView().progressVisible(false);
                getView().progressWait(false);
                getView().progressCanceledVisible(false);
                getView().progressErrorVisible(false);
                break;
            case PursesLoadTask.STATUS_WAITING:
                getView().progressVisible(true);
                getView().progressWait(true);
                getView().progressCanceledVisible(false);
                getView().progressErrorVisible(false);
                break;
            case PursesLoadTask.STATUS_RUNNING:
                getView().progressVisible(true);
                getView().progressWait(false);
                getView().progressCanceledVisible(false);
                getView().progressErrorVisible(false);
                break;
            case PursesLoadTask.STATUS_COMPLETE:
                getView().progressVisible(false);
                getView().progressWait(false);
                getView().progressCanceledVisible(false);
                getView().progressErrorVisible(false);
                break;
            case PursesLoadTask.STATUS_CANCELED:
                getView().progressVisible(false);
                getView().progressWait(false);
                getView().progressCanceledVisible(true);
                getView().progressErrorVisible(false);
                break;
            case PursesLoadTask.STATUS_ERROR:
                getView().progressVisible(false);
                getView().progressWait(false);
                getView().progressCanceledVisible(false);
                getView().progressErrorVisible(true);
                break;
        }
    }

    private void loadPurseResultChange() {
        if (!isWorking()) return;
        List<Purse> purses = pursesLoadTask == null ? null : pursesLoadTask.getResult();
        if (purses == null) purses = new ArrayList<>();
        boolean nothingData = purses.isEmpty();
        getView().setPurses(purses);
        getView().nothingDataVisible(nothingData);
    }

    private class PursesLoadTask {
        public static final int STATUS_NOT_RUNNING = 0;
        public static final int STATUS_WAITING = 1;
        public static final int STATUS_RUNNING = 2;
        public static final int STATUS_COMPLETE = 3;
        public static final int STATUS_CANCELED = 4;
        public static final int STATUS_ERROR = 5;

        private TaskInternal task;
        private int status;
        private List<Purse> result;

        public PursesLoadTask() {
            status = STATUS_NOT_RUNNING;
        }

        public final void start() {
            if (task == null) {
                task = new TaskInternal();
                setStatus(STATUS_WAITING);
                task.execute();
            }
        }

        public final void cancel() {
            if (task != null) {
                task.cancel(true);
            }
        }

        public final int getStatus() {
            return status;
        }

        private void setStatus(int status) {
            this.status = status;
            loadPurseStatusChange();
        }

        public final List<Purse> getResult() {
            return result;
        }

        private void setResult(List<Purse> result) {
            this.result = result;
            loadPurseResultChange();
        }

        private class TaskInternal extends AsyncTask<Void, Boolean, List<Purse>> {
            private boolean isError;

            @Override
            protected void onPreExecute() {
                setStatus(STATUS_RUNNING);
            }

            @Override
            protected List<Purse> doInBackground(Void... params) {
                try {
                    Thread.sleep(5000);
                    if (isCancelled()) return null;
                    return purseDao.get();
                } catch (InterruptedException e) {
                    return null;
                } catch (DaoException e) {
                    isError = true;
                    return null;
                }
            }

            @Override
            protected void onPostExecute(List<Purse> result) {
                if (isError) {
                    setResult(null);
                    setStatus(STATUS_ERROR);
                } else {
                    setResult(result);
                    setStatus(STATUS_COMPLETE);
                }
                task = null;
            }

            @Override
            protected void onCancelled() {
                setResult(null);
                setStatus(STATUS_CANCELED);
                task = null;
            }
        }
    }
}
